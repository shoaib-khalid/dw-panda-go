package com.kalsym.deliveryService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class SubmitOrderResult {

    public int deliveryProviderId;
    public DeliveryOrder orderCreated;
    public boolean isSuccess;
    public String message;
    public String status;
    public String systemTransactionId;
    public String orderId;
    public int resultCode;
    @JsonIgnore
    public String customerTrackingUrl;
    @JsonIgnore
    public String spTransactionId;

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
