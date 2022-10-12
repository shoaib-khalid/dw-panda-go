package com.kalsym.pandago.client.model.wrapper;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private String city;

    @JsonProperty("address")
    private String address;

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("state")
    private String state;

    @JsonProperty("contactName")
    private String contactName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("verticalCode")
    private String verticalCode;

    @JsonProperty("serviceChargesPercentage")
    private Double serviceChargesPercentage;

    @JsonProperty("paymentType")
    private String paymentType;

    @JsonProperty("invoiceSeqNo")
    private Integer invoiceSeqNo;

    @JsonProperty("regionCountryId")
    private String regionCountryId;

    @JsonProperty("costCenterCode")
    private String costCenterCode;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("providerId")
    int providerId;
}
