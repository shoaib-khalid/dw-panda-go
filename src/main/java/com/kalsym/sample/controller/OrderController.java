package com.kalsym.sample.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public class OrderController {

    //@PreAuthorize("hasAnyAuthority('panda-go-price-get', 'all')")
    public Object getPrice(HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "") String providerConfig,
                           @RequestParam(required = false, defaultValue = "") String orderObject,
                           @RequestParam(required = false, defaultValue = "123") String systemTransactionId,
                           @RequestParam(required = false, defaultValue = "true") String fulfillment) {
        //TODO: Implement the following flow:
        // 1. Send Login request
        // 2. Get Token
        // 3. Send get Price request
        return null;
    }

    public Object placeOrder(String providerConfig, String orderObject, String systemTransactionId) {
        return null;
    }

    public String getToken(String systemTransactionId, String url, String username, String password, Integer
            connectTimeout, Integer waitTimeout) {
        return null;
    }

    public Object getAddressId(String systemTransactionId, String address, String url, String key) {
        return null;
    }


}
