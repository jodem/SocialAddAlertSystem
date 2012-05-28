package fr.softquipeut.pocvertx.representation;

import com.google.common.collect.Lists;
import fr.softquipeut.pocvertx.channel.business.Action;
import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.util.URLBuilder;
import java.util.List;
import org.vertx.java.core.Vertx;


/***
 * Web representation of a channel
 * @author Joce
 */
public class ChannelRepresentation extends BasicRepresentation {
    
       
    private String $url;
    private int followers;
    private List<ActionRepresentation> actions;
        

    private ChannelRepresentation(Channel channel, URLBuilder urlBuilder) {
        super(channel.getId(), channel.getName());
        followers = channel.getFollowers();
        this.$url = urlBuilder.getAddress("api/channel/" + channel.getId());                
        Action subscribeAction = new Action("api/channel/" + channel.getId()+"/register/", "Register to the end of add event");
        Action alertAction = new Action("api/channel/" + channel.getId()+"/alert/", "Send an end of add alert");
        actions = Lists.newArrayList();
        actions.add(ActionRepresentation.of(subscribeAction));
        actions.add(ActionRepresentation.of(alertAction));
    }
    
    public static ChannelRepresentation of(Channel channel){        
        return new ChannelRepresentation(channel, URLBuilder.DEFAULT);
    }
    
    
    
}
