package com.sumasoft.stt.utils;

import java.security.*;

public class KeyGenerator {
    
    public void generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // set key size
        KeyPair keyPair = keyGen.generateKeyPair();

        // Get the private and public keys
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Print the keys
        System.out.println("Private key: " + privateKey);
        System.out.println("Public key: " + publicKey);
    }
}
