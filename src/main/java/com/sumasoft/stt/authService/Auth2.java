package com.sumasoft.stt.authService;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class Auth2 {

    public static String tokenString;
    public static String result;
    public static boolean b;
    static {
        tokenString=null;
        result=null;
        b=false;
    }

    public boolean demo(String str) throws Exception{
        TrustManager[] trustAllCerts;
        SSLContext sslContext;

        trustAllCerts= new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSLContext", e);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true)
                .build();


        String rq = "\""+str+"\"";
        // Set request body
        String requestBody = "{\"name\":"+rq+"}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);

        Request request = new Request.Builder()
                .url("https://192.168.221.41:3000/auth/token")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Process the response
                String responseBody = response.body().string();
                System.out.println("Response: " + responseBody);
                JsonObject jsonObject = new JsonObject();
                JsonParser parser = new JsonParser();
                JsonElement rootElement = parser.parse(responseBody.toString());
                jsonObject = rootElement.getAsJsonObject();
                tokenString= jsonObject.get("token").getAsString();
                System.out.println("response token is :"+ tokenString);
            }
        });
        Thread.sleep(2000);
        if(verifyToken(tokenString)){
            b=true;
        } else {
            b=false;
        }
        System.out.println("Final responseeeee ");
        return b;
    }

    private boolean verifyToken(String tokenString) throws Exception{
        System.out.println("Inside VerifyToken method");
        TrustManager[] trustAllCerts;
        SSLContext sslContext;
        trustAllCerts= new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSLContext", e);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true)
                .build();


        String rq = "\""+tokenString+"\"";
        // Set request body
        String requestBody = "{\"token\":"+rq+"}";
        System.out.println("payload is :"+requestBody.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);

        Request request = new Request.Builder()
                .url("https://192.168.221.41:3000/auth/verify-token")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Process the response
                String responseBody = response.body().string();
                System.out.println("Response: " + responseBody);
                JsonObject jsonObject = new JsonObject();
                JsonParser parser = new JsonParser();
                JsonElement rootElement = parser.parse(responseBody.toString());
                jsonObject = rootElement.getAsJsonObject();
                result = jsonObject.get("result").getAsString();
            }
        });
        Thread.sleep(2000);
        if(result.equals("true")){
            return true;
        }
        else {
            return false;
        }

    }


}
