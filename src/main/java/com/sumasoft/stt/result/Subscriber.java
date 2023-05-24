package com.sumasoft.stt.result;

public class Subscriber {

    public String name;

    public String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Subscriber(String name){
        this.name=name;
    }

    public void update(String message) {
       // System.out.println("Message Recived to subscriber :"+message.toString());
        setResponse(message);
    }

    public static Subscriber getSubscriber(String name){
        return new Subscriber(name);
    }

}
