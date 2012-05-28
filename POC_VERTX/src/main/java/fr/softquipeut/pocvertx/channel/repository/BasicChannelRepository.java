package fr.softquipeut.pocvertx.channel.repository;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.channel.business.Channels;
import fr.softquipeut.pocvertx.exceptions.ClientReadableException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joce
 */
public class BasicChannelRepository implements ChannelRepository {

    private static final ImmutableMap<String, Channel> CHANNEL_LIST_REF;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static {
        Builder<String, Channel> mapBuilder = ImmutableMap.<String, Channel>builder();
        for (Channel curChannel : Channels.ALL_FR_CHANNELS.getList()) {
            mapBuilder.put(curChannel.getId(), curChannel);
        }

        CHANNEL_LIST_REF = mapBuilder.build();
    }

    public Channels getAll() {

        
        //todo: add stats
        return Channels.ALL_FR_CHANNELS;

    }

    public Channel get(String ID) {
        Channel channel = CHANNEL_LIST_REF.get(ID);
        if (channel == null) {
            throw ClientReadableException.newNotFound("The requested channel :\"" + ID + "\" is not in the channel list");
        }
        return channel;
    }
}
