package com.kalsym.deliveryService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class PriceResult {
    public boolean isError;
    public String fulfillment;
    public String pickupDateTime;
    public BigDecimal price;
    public int resultCode;
    public String signature;
    public Integer interval;
    public String quotationId;
    public BigDecimal lat;
    public BigDecimal log;

}
