package com.sumasoft.stt.authService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Auth {
    ObjectMapper objectMapper;
    public Auth(){

        this.objectMapper=new ObjectMapper();
    }

    public boolean isValidUser(String email){
        try{

            // Create a trust manager that accepts all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            // Create a custom SSLContext with the trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // Set the custom SSLContext to trust all certificates
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);


            URL url = new URL("https://192.168.221.41:3000/auth/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable output and input streams
            connection.setDoOutput(true);
            connection.setDoInput(true);

            String rq = "\""+email+"\"";
            // Set request body
            String requestBody = "{\"name\":"+rq+"}";

            // Write the request body
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Close the connection
            connection.disconnect();

            JsonNode jsonNode = objectMapper.readTree(response.toString());
            if(jsonNode.has("token")){
                if(verifyToken(jsonNode.get("token").asText())){
                    return  true;
                }else {
                    return false;
                }

            }
        }

        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    private boolean verifyToken(String tokenString) throws Exception{

        URL url = new URL("https://192.168.221.41:3000/auth/verify-token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to POST
        connection.setRequestMethod("POST");

        // Enable output and input streams
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String rq = "\""+tokenString+"\"";
        // Set request body
        String requestBody = "{\"token\":"+rq+"}";

        // Write the request body
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(requestBody);
        outputStream.flush();
        outputStream.close();

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();


        // Close the connection
        connection.disconnect();

        JsonNode jsonNode = objectMapper.readTree(response.toString());
        if(jsonNode.has("result")){
            if(jsonNode.get("result").asText().equals("true")){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

}