package fr.softquipeut.pocvertx.representation;

import com.google.common.collect.Lists;
import fr.softquipeut.pocvertx.channel.business.Action;
import fr.softquipeut.pocvertx.util.URLBuilder;
import java.util.List;

/**
 *
 * @author Joce
 */
public class RootRepresentation {

    private List<ActionRepresentation> actions;

    private RootRepresentation() {
        actions = Lists.newArrayList();
        actions.add(ActionRepresentation.of(Action.LIST_CHANNEL));
    }

    public static RootRepresentation newInstance() {
        return new RootRepresentation();
    }
}
