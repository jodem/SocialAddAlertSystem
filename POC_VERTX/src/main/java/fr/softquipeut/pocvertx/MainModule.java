/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.softquipeut.pocvertx;

import fr.softquipeut.pocvertx.channel.repository.BasicChannelRepository;
import fr.softquipeut.pocvertx.channel.repository.ChannelRepository;
import fr.softquipeut.pocvertx.resource.ChannelResource;
import fr.softquipeut.pocvertx.resource.RootResource;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.core.parsetools.RecordParser;
import org.vertx.java.deploy.Verticle;

/**
 *
 * @author Joce
 */
public class MainModule extends Verticle {

    private  ChannelRepository channelRepo;
    private  ChannelResource channelResource;
    private final RootResource rootResource;
    private final Logger logger;
    
    public MainModule() {
        
        rootResource = new RootResource();        
        logger = LoggerFactory.getLogger(this.getClass());        
    }
    
    private void init(){
        channelRepo = new BasicChannelRepository(vertx);   
        channelResource = new ChannelResource(channelRepo, vertx);
    }

    @Override
    public void start() {
        
        init();
        
        RouteMatcher rm = new RouteMatcher();

        rm.get("/api/", rootResource.getRoot());
        rm.get("/api/channel/", channelResource.getListChannelHandler());
        rm.get("/api/channel/:id", channelResource.getOneChannelHandler());
        rm.get("/api/channel/:id/register/",channelResource.subscribe());             
        rm.get("/api/channel/:id/alert/", channelResource.alert());

        vertx.createHttpServer().requestHandler(rm).listen(80);

        
    }
}
