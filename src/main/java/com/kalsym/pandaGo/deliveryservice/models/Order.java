/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandaGo.deliveryservice.models;

import com.kalsym.pandaGo.deliveryservice.models.enums.ItemType;
import com.kalsym.pandaGo.deliveryservice.models.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author user
 */
@Getter
@Setter
@ToString
public class Order {
    String customerId;
    Integer merchantId;
    String productCode;
    Integer deliveryProviderId;
    ItemType itemType;
    String transactionId;
    String orderId;
    String shipmentContent;
    Integer pieces;
    boolean isInsurance;
    Double shipmentValue;
    String storeId;
    String cartId;
    Pickup pickup;
    @Valid
    Delivery delivery;
    String regionCountry;
    Boolean serviceType;
    VehicleType vehicleType;
    String deliveryPeriod;
    Integer interval;
    //   String deliveryService;
    String remarks;
    String deliveryType;
    Double totalWeightKg;
    BigDecimal height;
    BigDecimal width;
    BigDecimal length;
    String pickupTime;

}
