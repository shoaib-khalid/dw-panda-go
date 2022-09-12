package com.kalsym.deliveryService.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryOrderResult {

    public int providerId;
    public DeliveryOrder orderFound;
    public boolean isSuccess;
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
