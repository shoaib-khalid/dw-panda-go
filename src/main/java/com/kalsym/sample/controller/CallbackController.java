package com.kalsym.sample.controller;


import com.kalsym.pandago.client.api.CallbackApi;
import com.kalsym.pandago.client.api.OrdersApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.*;
import com.kalsym.sample.utility.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/callback")
public class CallbackController {


    @PostMapping(path = {""}, name = "order-post", produces = "application/json")
    public String callback(HttpServletRequest request, @RequestBody CallbackRequest callbackRequest) {
        Logger.application.info("callback request received");

        // 1. Receive the request from delivery-service
        // 2. TOdo: process the callback request
        try {
            Logger.application.info("Received callback request: [" + callbackRequest.toJSON() + "]");
            return callbackRequest.toJSON();
        } catch
        (Exception exp) {
            Logger.application.error("Exception in receiveing callback", exp);
            return "ERROR";
        }
    }


}
