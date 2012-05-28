package fr.softquipeut.pocvertx.representation;

import fr.softquipeut.pocvertx.channel.business.Action;
import fr.softquipeut.pocvertx.util.URLBuilder;

/**
 *
 * @author Joce
 */
public class ActionRepresentation{

    private String url;
    private String description;

    private ActionRepresentation(String url, String description, URLBuilder urlBuilder) {        
        this.url = urlBuilder.getAddress(url);
        this.description = description;        
    }

    public static ActionRepresentation of(Action action) {
        return new ActionRepresentation(action.getActionUrl(), action.getDescription(), URLBuilder.DEFAULT);
    }
}
