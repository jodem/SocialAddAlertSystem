package fr.softquipeut.pocvertx.channel.business;

import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 *
 * @author Joce
 */
public class Channel {

    private final String id;
    private final String name;
    private final int followers;

    public Channel(String id, String name, int followers) {
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.followers = followers;
    }
    
    public Channel(String id, String name){
        this(id, name, 0);
        
    }

    public int getFollowers() {
        return followers;
    }

   
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Channel other = (Channel) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
