/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandaGo.deliveryservice.models.daos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author user
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Provider implements Serializable {
    @Id
    Integer id;
    String name;
    String address;
    String contactNo;
    String contactPerson;
    @JsonIgnore
    String getPriceClassname;
    @JsonIgnore
    String submitOrderClassname;
    @JsonIgnore
    String cancelOrderClassname;
    @JsonIgnore
    String queryOrderClassname;
    @JsonIgnore
    String spCallbackClassname;
    @JsonIgnore
    String pickupDateClassname;
    @JsonIgnore
    String pickupTimeClassname;
    @JsonIgnore
    String locationIdClassname;
    @JsonIgnore
    String airwayBillClassName;
    String providerImage;
    @JsonIgnore
    String driverDetailsClassName;
    @JsonIgnore
    String additionalQueryClassName;
    @JsonIgnore
    String addPriorityClassName;
    ;
    //    String regionCountryId;
    @JsonIgnore
    Integer minimumOrderQuantity;
    @JsonIgnore
    Boolean retry;
    @JsonIgnore
    Boolean remark;
    Boolean scheduleDate;
}
