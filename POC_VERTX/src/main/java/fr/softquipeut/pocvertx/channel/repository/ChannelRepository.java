package fr.softquipeut.pocvertx.channel.repository;

import fr.softquipeut.pocvertx.channel.business.Channel;
import fr.softquipeut.pocvertx.channel.business.Channels;

/**
 *
 * @author Joce
 */
public interface ChannelRepository {
      public Channels getAll();
      public Channel get(String ID);
}
