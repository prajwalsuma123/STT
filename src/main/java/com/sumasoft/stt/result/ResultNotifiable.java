package com.sumasoft.stt.result;

public abstract class ResultNotifiable implements Notifiable{
    
    public ResultNotifiable(){
    }

    @Override
    public void partialText(String message) {
        OnpartialText(message);
    }

    @Override
    public void resultText(String message) {
        OnResultText(message);
    }
    
    public abstract void OnpartialText(String st);
    public abstract void OnResultText(String st);
}
