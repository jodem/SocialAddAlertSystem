package fr.softquipeut.pocvertx.channel.business;

import com.google.common.collect.ImmutableList;

/**
 *
 * @author Joce
 */
public final class Channels {
    
    
    public static final Channels ALL_FR_CHANNELS;
    
    static{
        ImmutableList.Builder<Channel> channelBuilder = new ImmutableList.Builder<Channel>();
        channelBuilder.add(new Channel("FR_TF1", "TF1"));
        channelBuilder.add(new Channel("FR_FR2", "France 2"));
        channelBuilder.add(new Channel("FR_FR3", "France 3"));
        channelBuilder.add(new Channel("FR_CP", "Cannal +"));
        channelBuilder.add(new Channel("FR_M6", "M6"));        
        
        ALL_FR_CHANNELS = new Channels(channelBuilder.build(), "FR", "Chaines France");        
    }
    
    
    private final ImmutableList<Channel> list;
    private final String id;
    private final String name;

    public ImmutableList<Channel> getList() {
        return list;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    
    public Channels(ImmutableList<Channel> channels, String id, String name){
        this.list = channels;
        this.id = id;
        this.name = name;
    }
    
}
