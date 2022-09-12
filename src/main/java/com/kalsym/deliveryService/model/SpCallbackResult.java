package com.kalsym.deliveryService.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SpCallbackResult {

    public int providerId;
    public String spOrderId;
    public String status;
    public String driverId;
    public String riderName;
    public String riderPhone;
    public String systemStatus;
    public String trackingUrl;
    public String driveNoPlate;
    public int resultCode;
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
