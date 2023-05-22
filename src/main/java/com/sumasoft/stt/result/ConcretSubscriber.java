package com.sumasoft.stt.result;

public class ConcretSubscriber implements Subscriber{
    public String name;
    
    public ConcretSubscriber(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String update(String msg) {
        System.out.println("Message recived in concret :"+msg.toString());
        return msg;
    }
    
    public ConcretSubscriber getInstance(String name){
        return new ConcretSubscriber(name);
    }
}
