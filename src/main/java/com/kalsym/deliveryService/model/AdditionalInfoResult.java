package com.kalsym.deliveryService.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdditionalInfoResult {


    public int providerId;
    public String costCentreCode;
    public String storeId;
    public boolean isSuccess;
    public String message;
    public int resultCode;
}
