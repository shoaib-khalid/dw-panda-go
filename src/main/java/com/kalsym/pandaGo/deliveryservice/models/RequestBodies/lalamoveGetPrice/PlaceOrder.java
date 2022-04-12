package com.kalsym.pandaGo.deliveryservice.models.RequestBodies.lalamoveGetPrice;

import lombok.ToString;

@ToString
public class PlaceOrder extends GetPrices {
    public QuotedTotalFee quotedTotalFee;
    public boolean sms;
    public boolean pod;
    public String fleetOption;

    public PlaceOrder(GetPrices getPrice, QuotedTotalFee quotedTotalFee) {
        super(getPrice.serviceType, getPrice.specialRequests, getPrice.stops, getPrice.requesterContact, getPrice.deliveries, getPrice.scheduleAt);
        this.quotedTotalFee = quotedTotalFee;
    }
}


