package com.kalsym.pandago.client.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Pickup {

    @JsonProperty("parcelReadyTime")
    String parcelReadyTime;
    @JsonProperty("pickupDate")
    String pickupDate;
    @JsonProperty("pickupTime")
    String pickupTime;
    @JsonProperty("endPickupDate")
    String endPickupDate;
    @JsonProperty("endPickupTime")
    String endPickupTime;
    @JsonProperty("pickupOption")
    String pickupOption;
    @JsonProperty("vehicleType")
    VehicleType vehicleType;
    @JsonProperty("pickupAddress")
    String pickupAddress;
    @JsonProperty("pickupPostcode")
    String pickupPostcode;
    @JsonProperty("pickupState")
    String pickupState;
    @JsonProperty("pickupCountry")
    String pickupCountry;
    @JsonProperty("pickupCity")
    String pickupCity;
    @JsonProperty("pickupLocationId")
    Integer pickupLocationId;
    @JsonProperty("pickupContactName")
    String pickupContactName;
    @JsonProperty("pickupContactPhone")
    String pickupContactPhone;
    @JsonProperty("pickupContactEmail")
    String pickupContactEmail;
    @JsonProperty("isTrolleyRequired")
    boolean isTrolleyRequired;
    @JsonProperty("remarks")
    String remarks;
    @JsonProperty("pickupZone")
    String pickupZone;
    @JsonProperty("costCenterCode")
    String costCenterCode;
    @JsonProperty("latitude")
    BigDecimal latitude;
    @JsonProperty("longitude")
    BigDecimal longitude;

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }



}
