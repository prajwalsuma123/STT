package com.sumasoft.stt.user;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumasoft.stt.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.io.File;

/**
 * This class is used to Aunthenticate the User
 */
public class AunthenticateUser {
    
    public static final Logger logger= LoggerFactory.getLogger(AunthenticateUser.class);
    
    public static ArrayList<String> subscriptionKeyArrayList=new ArrayList<>();
    

    /**
     * Aunthenticate the User 
     * @param key
     * @return
     * @throws IOException
     */
    public boolean aunthenticateUser(String key) throws IOException {
        logger.info("User Authenticating");
        ArrayList<String> result=new ArrayList<>();
        result=readFile();
        int i=0;
        for(i=0;i<result.size();i++)
        {
            if(key.equalsIgnoreCase(result.get(i))){
                break;
            }
        }
        if(i<result.size())
        {
            return true;
        }
        return false;
    }

    /**
     * retrive the data from json file and stores the subscription keys
     * @return
     * @throws IOException
     */
    public static ArrayList<String> readFile(){
        logger.info("reading user data from file");
        ObjectMapper objectMapper = new ObjectMapper();
        File file=new File("/home/prajwal.sonawane/Desktop/UserData/users.json");

        try {
            JsonNode rootNode = objectMapper.readTree(file);
            rootNode.size();

            JsonNode arrayNode = rootNode.get("users");

            for (JsonNode objNode : arrayNode) {
                String username = objNode.get("username").asText();
                String password = objNode.get("password").asText();
                String subscriptionkey = objNode.get("subscriptionkey").asText();

                subscriptionKeyArrayList.add(subscriptionkey);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        logger.info("read data sucessfully from file");
        return subscriptionKeyArrayList;
    }
}
