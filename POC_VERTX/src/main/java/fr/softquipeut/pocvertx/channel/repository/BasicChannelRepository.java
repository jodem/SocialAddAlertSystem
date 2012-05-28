package fr.softquipeut.pocvertx.channel.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.channel.business.Channels;
import fr.softquipeut.pocvertx.exceptions.ClientReadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;

/**
 *
 * @author Joce
 */
public class BasicChannelRepository implements ChannelRepository {

    private static final ImmutableMap<String, Channel> CHANNEL_LIST_REF;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Vertx vertx;

    static {
        Builder<String, Channel> mapBuilder = ImmutableMap.<String, Channel>builder();
        for (Channel curChannel : Channels.ALL_FR_CHANNELS.getList()) {
            mapBuilder.put(curChannel.getId(), curChannel);
        }

        CHANNEL_LIST_REF = mapBuilder.build();
    }

    public BasicChannelRepository(Vertx vertx) {
        this.vertx = vertx;
    }

    public Channels getAll() {

        ImmutableList.Builder<Channel> channelBuilder = new ImmutableList.Builder<Channel>();

        for (Channel channel : Channels.ALL_FR_CHANNELS.getList()) {
            int nbrOfFollowers = vertx.sharedData().getSet(channel.getId()).size();
            channelBuilder.add(new Channel(channel.getId(), channel.getName(), nbrOfFollowers));
        }


        return new Channels(channelBuilder.build(), Channels.ALL_FR_CHANNELS.getId(), Channels.ALL_FR_CHANNELS.getName());

    }

    public Channel get(String ID) {
        Channel channel = CHANNEL_LIST_REF.get(ID);
        if (channel == null) {
            throw ClientReadableException.newNotFound("The requested channel :\"" + ID + "\" is not in the channel list");
        }
        return channel;
    }
}
