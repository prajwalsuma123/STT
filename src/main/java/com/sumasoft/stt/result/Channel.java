package com.sumasoft.stt.result;

import com.sumasoft.stt.client.AudioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Those who subsciber Channel will recives update
 */
//Observer design pattern
public class Channel {
    public static final Logger logger= LoggerFactory.getLogger(Channel.class);

    /**
     * list of subscribers who subscribe Channel
     */
    public List<ConcretSubscriber> subs=new ArrayList<>();
    
    public void subscriber(ConcretSubscriber subscriber){
        this.subs.add(subscriber);
        logger.info("Subscriber added :");
    }

    public void Unsubscriber(ConcretSubscriber subscriber){
        this.subs.remove(subscriber);
        logger.info("Subcriber removed :");
    }
    
    public void notifySubscriber(String message){
        for(ConcretSubscriber subscriber:subs){
            subscriber.update(message);
        }
       // logger.info("Notification send to subscribers");
    }
}
