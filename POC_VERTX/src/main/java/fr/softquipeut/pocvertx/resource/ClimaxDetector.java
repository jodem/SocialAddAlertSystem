package fr.softquipeut.pocvertx.resource;

import fr.softquipeut.pocvertx.channel.business.Channel;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;

/**
 *
 * @author Joce
 */
public class ClimaxDetector {
    
    //todo: add unit test!

    private static final String TIMER_KEY = "chanTimer";
    private static final int TIMER_TICK_IN_MILLI = 4000;
    private Vertx vertx;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ClimaxDetector(Vertx vertx) {
        this.vertx = vertx;
    }

    public void alertRecieved(final Channel channel) {
        
        //todo: stocker la demande dans une set par clientid avec date ....?
        
        saveAlertRequest(channel);
        
        startClimaxDetection(channel);
        
    }
    
    
    /**
     * Save the alert request to the alert channel set.
     * @param channel 
     */
    private void saveAlertRequest(Channel channel){
        //todo : implémenter
        // atomic increment dans hazelcast ????
       
    }
    
    
    
    /**
     * Start the alert climax detection if necessary.
     * @param channel 
     */
    private void startClimaxDetection(final Channel channel){
        // analyser les x dernières secondes d'alertes sur la chaine
        boolean hasRunningDetector = getChannelTimerValue(channel);
        if (!hasRunningDetector) {
            /**
             * todo: check si alert pas envoyée récemment ...
             * avoir une map des dates de denière alerte pour une chaine donnée
             */      
            setRunningAnalyzer(channel, true);            
            vertx.setTimer(TIMER_TICK_IN_MILLI, new Handler<Long>() {

                public void handle(Long e) {
                    //todo: regarder si pic existe
                    logger.info("Trying to get a climax on channel " + channel.getId());
                    Set<String> channelSet = vertx.sharedData().getSet(channel.getId());
                    for (String subscriptionId : channelSet) {
                        try {
                            //todo : mettre un event id
                            vertx.eventBus().send(subscriptionId, "done !");
                        } catch (Exception exp) {
                            // prevent from bugging
                            logger.info("Unabe to deliver an alert message", exp);
                        }
                    }
                    channelSet.clear();
                    //todo : maj d'une map de dernière alerte
                    setRunningAnalyzer(channel, false);
                }
                
            });
        }
    }
    

    /**
     *
     * @param channel channel analyzer running state to fetch
     * @return the channel running state, create one if necessary
     */
    private boolean getChannelTimerValue(Channel channel) {
        Map<String, Boolean> channelTimerMap = vertx.sharedData().getMap(TIMER_KEY);
        if (!channelTimerMap.containsKey(channel.getId())) {
            channelTimerMap.put(channel.getId(), Boolean.FALSE);
        }
        return channelTimerMap.get(channel.getId());
    }

    private void setRunningAnalyzer(Channel channel, boolean running) {
        Map<String, Boolean> channelTimerMap = vertx.sharedData().getMap(TIMER_KEY);
        channelTimerMap.put(channel.getId(), running);
    }
}
