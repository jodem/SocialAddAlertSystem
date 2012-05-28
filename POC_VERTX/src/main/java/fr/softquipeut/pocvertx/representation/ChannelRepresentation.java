package fr.softquipeut.pocvertx.representation;

import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.util.URLBuilder;


/***
 * Web representation of a channel
 * @author Joce
 */
public class ChannelRepresentation extends BasicRepresentation {
    
       
    private String $url;
    private long followers;
        

    private ChannelRepresentation(Channel channel, URLBuilder urlBuilder) {
        super(channel.getId(), channel.getName());
        followers = channel.getFollowers();
        this.$url = urlBuilder.getAddress("api/channel/" + channel.getId());                
    }
    
    public static ChannelRepresentation of(Channel channel){
        return new ChannelRepresentation(channel, URLBuilder.DEFAULT);
    }
    
    
    
}
