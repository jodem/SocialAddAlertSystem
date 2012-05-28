/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.softquipeut.pocvertx;


import fr.softquipeut.pocvertx.channel.repository.BasicChannelRepository;
import fr.softquipeut.pocvertx.channel.repository.ChannelRepository;
import fr.softquipeut.pocvertx.resource.ChannelResource;
import fr.softquipeut.pocvertx.resource.RootResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.deploy.Verticle;

/**
 *
 * @author Joce
 */
public class MainModule extends Verticle{
    
    
    private final ChannelRepository channelRepo;
    private final ChannelResource channelResource;
    private final RootResource rootResource;
    
    private final Logger logger;

    public MainModule() {
        channelRepo = new BasicChannelRepository();        
        channelResource = new ChannelResource(channelRepo);
        rootResource = new RootResource();
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    
    
    @Override
    public void start() {                
        
       RouteMatcher rm = new RouteMatcher();
       
       rm.get("/api/", rootResource.getRoot());
       rm.get("/api/channel/", channelResource.getListChannelHandler());
       rm.get("/api/channel/:id", channelResource.getOneChannelHandler());     
       
        
       vertx.createHttpServer().requestHandler(rm).listen(80);
        
    }
    
}
