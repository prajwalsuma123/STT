package com.sumasoft.stt.utils;

import com.sumasoft.stt.user.AunthenticateUser;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * This class will stores the subscription key 
 */
public class SpeechConfig {

    private String subsriptionKey;

    public SpeechConfig(String key){
        this.subsriptionKey=key;
    }

    @Autowired
    public AunthenticateUser aunthenticateUser;

    /**
     * Authenticate the user using subscription key
     * @param key
     * @return
     * @throws IOException
     */
    public boolean fromSubscriptionKey(String key) throws IOException {
        return aunthenticateUser.aunthenticateUser(key);
    }
}
