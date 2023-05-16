package com.sumasoft.stt.response;

public class Response {
    
    private String message;
    
    public String partialText;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getPartialText(){
        return "";
    }
    public void setPartialText(String partialText) {
        this.partialText = partialText;
    }
}
