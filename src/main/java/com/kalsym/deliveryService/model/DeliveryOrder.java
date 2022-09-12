package com.kalsym.deliveryService.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliveryOrder {
    String spOrderId;
    String spOrderName;
    String vehicleType;
    String status;
    String createdDate;
    String customerTrackingUrl;
    String systemStatus;

    //DRIVER INFORMATION
    String driverId;
    String riderName;
    String riderPhoneNo;
    String riderCarPlateNo;

}
