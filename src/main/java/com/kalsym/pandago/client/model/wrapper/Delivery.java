/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandago.client.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//import javax.validation.constraints.NotBlank;

/**
 * @author user
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery {

    @JsonProperty("deliveryAddress")
    String deliveryAddress;

    @JsonProperty("deliveryPostcode")
    String deliveryPostcode;

    @JsonProperty("deliveryState")
    String deliveryState;

    @JsonProperty("deliveryCity")
    String deliveryCity;

    @JsonProperty("deliveryCountry")
    String deliveryCountry;

    @JsonProperty("deliveryContactName")
    String deliveryContactName;

    @JsonProperty("deliveryContactPhone")
    String deliveryContactPhone;

    @JsonProperty("deliveryContactEmail")
    String deliveryContactEmail;

    @JsonProperty("deliveryZone")
    String deliveryZone;

    @JsonProperty("latitude")
    BigDecimal latitude;


    @JsonProperty("longitude")
    BigDecimal longitude;

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}