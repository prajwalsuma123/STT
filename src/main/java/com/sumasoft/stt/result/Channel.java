package com.sumasoft.stt.result;

import java.util.*;

/**
 * It maintain List of subscibers and notify the subscriber
 */
public class Channel {
    
    private List<Subscriber> subs=new ArrayList<>();
    
    public void addSubscriber(Subscriber subscriber){
        subs.add(subscriber);
    }
    
    public void removeSubscriber(Subscriber subscriber){
        subs.remove(subscriber);
    }
    
    public void notifySubscriber(String message){
        for(Subscriber subscriber:subs){
            subscriber.update(message);
        }
    }
}
