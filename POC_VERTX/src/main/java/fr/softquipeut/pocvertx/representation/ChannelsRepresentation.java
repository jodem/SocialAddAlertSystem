package fr.softquipeut.pocvertx.representation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.channel.business.Channels;
import fr.softquipeut.pocvertx.util.URLBuilder;

/**
 *
 * @author Joce
 */
public final class ChannelsRepresentation{

    private ImmutableList<ChannelRepresentation> channels;
    private String $url;
    
    private ChannelsRepresentation(Channels channels, URLBuilder urlBuilder){        
        this.channels = loadChannels(channels);
        this.$url = urlBuilder.getAddress("api/channel/");
    }
    
    private ImmutableList<ChannelRepresentation> loadChannels(Channels channels){
        Builder<ChannelRepresentation> channelRepresBuilder = ImmutableList.<ChannelRepresentation>builder();
        for (Channel curChannel : channels.getList()){
            channelRepresBuilder.add(ChannelRepresentation.of(curChannel));
        }        
        return channelRepresBuilder.build();                
    }
    
    public static ChannelsRepresentation of(Channels channels){
        return new ChannelsRepresentation(channels, URLBuilder.DEFAULT);
    }
    
    
}
