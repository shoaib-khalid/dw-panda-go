package com.kalsym.pandaGo.deliveryservice;

import org.springframework.beans.factory.annotation.Value;

public class ConsignmentNoteHandler {
    @Value("${productServiceURL}")
    String productServiceURL;

    public String storeAirwayBill(byte[] bytes) {
        System.out.println("TRY to print here" + productServiceURL);
        return "";
    }


}



