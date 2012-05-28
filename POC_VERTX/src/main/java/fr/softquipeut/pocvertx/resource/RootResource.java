package fr.softquipeut.pocvertx.resource;

import com.google.gson.Gson;
import fr.softquipeut.pocvertx.channel.business.Channels;
import fr.softquipeut.pocvertx.representation.ChannelsRepresentation;
import fr.softquipeut.pocvertx.representation.RootRepresentation;
import fr.softquipeut.pocvertx.util.JsonResponseHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

/**
 *
 * @author Joce
 */
public class RootResource {
    
    
    
     public Handler<HttpServerRequest> getRoot() {
        return new JsonResponseHandler() {

            @Override
            public void handleRequest(HttpServerRequest request) {                
                request.response.end(new Gson().toJson(RootRepresentation.newInstance()));
            }
        };
    }
    
    
}
