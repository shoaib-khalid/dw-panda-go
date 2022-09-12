package com.kalsym.pandago.client.model.wrapper;

//import com.kalsym.deliveryservice.models.enums.ItemType;
//import com.kalsym.deliveryservice.models.enums.VehicleType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author user
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @JsonProperty("customerId")
    String customerId;
    @JsonProperty("merchantId")
    Integer merchantId;
    @JsonProperty("productCode")
    String productCode;
    @JsonProperty("deliveryProviderId")
    Integer deliveryProviderId;
    ////    ItemType itemType;
    @JsonProperty("transactionId")
    String transactionId;
    @JsonProperty("orderId")
    String orderId;
    @JsonProperty("shipmentContent")
    String shipmentContent;
    @JsonProperty("pieces")
    Integer pieces;
    @JsonProperty("isInsurance")
    boolean isInsurance;
    @JsonProperty("shipmentValue")
    Double shipmentValue;
    @JsonProperty("orderAmount")
    Double orderAmount;
    @JsonProperty("paymentType")
    String paymentType;
    @JsonProperty("storeId")
    String storeId;
    @JsonProperty("cartId")
    String cartId;

        Pickup pickup;
//    @Valid
    Delivery delivery;
    @JsonProperty("regionCountry")
    String regionCountry;
    @JsonProperty("serviceType")
    Boolean serviceType;
    ////    VehicleType vehicleType;
    @JsonProperty("deliveryPeriod")
    String deliveryPeriod;
    @JsonProperty("interval")
    Integer interval;
    @JsonProperty("remarks")
    String remarks;
    @JsonProperty("deliveryType")
    String deliveryType;
    @JsonProperty("totalWeightKg")
    Double totalWeightKg;
    @JsonProperty("height")
    BigDecimal height;
    @JsonProperty("width")
    BigDecimal width;
    @JsonProperty("length")
    BigDecimal length;
    @JsonProperty("pickupTime")
    String pickupTime;
    @JsonProperty("signature")
    String signature;
    @JsonProperty("codAmount")
    BigDecimal codAmount;
    @JsonProperty("totalParcel")
    Integer totalParcel;

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
