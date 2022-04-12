package com.kalsym.pandaGo.deliveryservice.models.daos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiderDetails {
    @JsonIgnore
    String driverId;
    String name;
    String phoneNumber;
    String plateNumber;
    String trackingUrl;
    String orderNumber;
    Provider provider;
    String airwayBill;
    String riderStatus;
}
