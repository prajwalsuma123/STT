package com.sumasoft.stt.result;

/**
 * Those who subscriber the Channel class will recived update 
 */
public interface Subscriber {

//    public String name;
//    public String response;
//
//    public String getResponse() {
//        return response;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }
//
//    public Subscriber(String name){
//        this.name=name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void update(String message, String name){
//        // System.out.println("Message recived for Subscriber :"+name+"\tMessage is :\t"+message.toString());
//        this.setResponse(message);
//    }
//
//    public static Subscriber getSubcriber(String name){
//        return new Subscriber(name);
//    }
    
    public String update(String msg);

}
