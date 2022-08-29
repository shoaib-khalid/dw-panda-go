/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandago.client.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.NotBlank;

/**
 * @author user
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery {
//    @NotBlank(message = "deliveryAddress is mandatory")
    String deliveryAddress;
//    @NotBlank(message = "deliveryPostcode is mandatory")
    String deliveryPostcode;
//    @NotBlank(message = "deliveryState is mandatory")
    String deliveryState;
//    @NotBlank(message = "deliveryCity is mandatory")
    String deliveryCity;

//    @NotBlank(message = "deliveryCountry is mandatory")
    String deliveryCountry;

//    @NotBlank(message = "deliveryContactName is mandatory")
    String deliveryContactName;
//    @NotBlank(message = "deliveryContactPhone is mandatory")
    String deliveryContactPhone;
//    @NotBlank(message = "deliveryContactEmail is mandatory")
    String deliveryContactEmail;
    String deliveryZone;

}