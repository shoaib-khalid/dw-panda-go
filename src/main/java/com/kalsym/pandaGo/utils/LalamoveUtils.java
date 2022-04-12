package com.kalsym.pandaGo.utils;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LalamoveUtils {

    public static HttpEntity<String> composeRequest(String ENDPOINT_URL, String METHOD, JSONObject bodyJson, HttpHeaders headers, String secretKey, String apiKey) throws NoSuchAlgorithmException, InvalidKeyException {
//        String secretKey = "H1vOco2bIznGUaEsOe4rH4ImAP+cFVzmROVkdw54hX1SzfFoF1klMbgFyTBceo0R";
//        String apiKey = "5db99569ffae83621ca990e2589ca5d1";
     /*   String secretKey = "7p0CJjVxlfEpg/EJWi/y9+6pMBK9yvgYzVeOUKSYZl4/IztYSh6ZhdcdpRpB15ty";
        String apiKey = "6e4e7adb5797632e54172dc2dd2ca748";*/

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secret_key);

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String rawSignature = timeStamp+"\r\n"+METHOD+"\r\n"+ENDPOINT_URL+"\r\n\r\n"+bodyJson.toString();
        byte[] byteSig = mac.doFinal(rawSignature.getBytes());
        String signature = DatatypeConverter.printHexBinary(byteSig);
        signature = signature.toLowerCase();

        String authToken = apiKey+":"+timeStamp+":"+signature;

        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "hmac "+authToken);
        headers.set("X-LLM-Country", "MY_KUL");
        HttpEntity<String> request = new HttpEntity(bodyJson.toString(), headers);

        return request;
    }

    public static String hash(String ENDPOINT_URL, String METHOD, JSONObject bodyJson) throws NoSuchAlgorithmException, InvalidKeyException {
        String secretKey = "7p0CJjVxlfEpg/EJWi/y9+6pMBK9yvgYzVeOUKSYZl4/IztYSh6ZhdcdpRpB15ty";
        String apiKey = "6e4e7adb5797632e54172dc2dd2ca748";

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secret_key);

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String rawSignature = bodyJson.get("timestamp")+"\r\n"+METHOD+"\r\n"+ENDPOINT_URL+"\r\n\r\n"+bodyJson.toString();
        byte[] byteSig = mac.doFinal(rawSignature.getBytes());
        String signature = DatatypeConverter.printHexBinary(byteSig);
        signature = signature.toLowerCase();

        String authToken = apiKey+":"+timeStamp+":"+signature;

        return signature;
    }

}