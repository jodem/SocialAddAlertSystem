package fr.softquipeut.pocvertx.channel.business;

import com.google.common.base.Preconditions;

/**
 *
 * @author Joce
 */
public class Action {
    
    private String url;
    private String description;
    public static final Action LIST_CHANNEL = new Action("api/channel/", "List the availlable channels");

    public Action(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

  
    /**
     * @return URL without the server suffix
     */
    public String getActionUrl() {
        return url;
    }
}
