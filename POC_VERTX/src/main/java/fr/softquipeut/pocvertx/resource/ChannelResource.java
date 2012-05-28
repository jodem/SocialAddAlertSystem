package fr.softquipeut.pocvertx.resource;

import fr.softquipeut.pocvertx.util.JsonResponseHandler;
import com.google.gson.Gson;
import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.channel.business.Channels;
import fr.softquipeut.pocvertx.channel.repository.ChannelRepository;
import fr.softquipeut.pocvertx.representation.ChannelRepresentation;
import fr.softquipeut.pocvertx.representation.ChannelsRepresentation;
import fr.softquipeut.pocvertx.util.HtmlResponseHandler;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;

public final class ChannelResource {

    private final ChannelRepository channelRepo;
    private final static String ID_PARAM = "id";
    private final Logger logger;
    

    public ChannelResource(ChannelRepository channelRepo) {
        this.channelRepo = channelRepo;        
        logger = LoggerFactory.getLogger(this.getClass());
        
    }

    public Handler<HttpServerRequest> getListChannelHandler() {
        return new JsonResponseHandler() {

            @Override
            public void handleRequest(HttpServerRequest request) {
                Channels all = channelRepo.getAll();
                ChannelsRepresentation channelsRepre = ChannelsRepresentation.of(all);
                request.response.end(new Gson().toJson(channelsRepre));
            }
        };
    }

    public Handler<HttpServerRequest> getOneChannelHandler() {
        return new JsonResponseHandler() {

            @Override
            public void handleRequest(HttpServerRequest request) {
                String requestedChannelId = request.params().get(ID_PARAM);
                logger.debug("Request channel with ID {}", requestedChannelId);
                ChannelRepresentation chanelRep = ChannelRepresentation.of(channelRepo.get(requestedChannelId));
                request.response.end(new Gson().toJson(chanelRep));
            }
        };
    }

    public Handler<HttpServerRequest> subscribe(final Vertx vertx) {
        return new HtmlResponseHandler() {

            @Override
            public void handleRequest(final HttpServerRequest request) {

                final String channelId = request.params().get("id");
                Channel requestChannel = channelRepo.get(channelId);// ensure the channel exists                
                Set<String> channelSet = vertx.sharedData().getSet(requestChannel.getId());
                final String subscriptionId = UUID.randomUUID().toString();
                channelSet.add(subscriptionId);
                vertx.eventBus().registerHandler(subscriptionId, new Handler<Message<String>>() {

                    public void handle(Message e) {
                        vertx.eventBus().unregisterHandler(subscriptionId);
                        request.response.end("<html><body>Alert recieved !</body></html>");
                    }
                });
            }
        };
    }

    public Handler<HttpServerRequest> alert(final Vertx vertx) {
        return new JsonResponseHandler() {

            @Override
            public void handleRequest(final HttpServerRequest request) {

                final String channelId = request.params().get("id");
                Channel requestChannel = channelRepo.get(channelId); // ensure the channel exists                
                Set<String> channelSet = vertx.sharedData().getSet(requestChannel.getId());                
                int followers = channelSet.size();
                for (String subscriptionId : channelSet) {
                    vertx.eventBus().send(subscriptionId, "done !");                    
                }
                channelSet.clear();
                request.response.end("{\"response\" : \"sent to " + followers +" user(s)\"}");
            }
        };
    }
}
