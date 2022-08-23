/*
 * pandago API
 * # About The pandago API provides system-to-system integration to facilitate on-demand courier delivery service requests. Each integration is scoped as a specific Brand using a ClientID. Each delivery request will be called an Order.  ## Client Client represents a single integration for a specific Brand. Client information will include the following: * Customer known name of the Brand/Branch * Address of the Brand/Branch that includes Latitude and Longitude.  A Client would typically represent a logistical system that manages a Brand with a few branch locations. This system will usually manage or will need an order allocation to distribute order to its branches. The pandago API supports dynamic pick-up location that allows orders to be picked up from any branch location.  ### Integration Step(s) 1. Register a Client as a Brand with pandago. 2. Register all Branches/Outlets which relates to the Brand.     *Note: Accuracy of branch’s latitude and longitude is important for the branch matching process* 3. Use the same ClientID when submitting orders from any branch within the registered Brand. ### Ordering Step(s) 1. The Sender address must be specified when submitting an Order.     The Sender latitude and longitude will be used to find the matching Branch/Outlet with a tight tolerance.     *Note: system will reject the order when it fails to find the matching Branch/Outlet latitude and longitude.*  ## Order Order is the principal object that encompasses the package delivery process from the start to the end. Once a new order is submitted, pandago will respond with the order details and will start looking for an available courier. OrderID should be used to get updates on the order status.  ### Status Updates Order status updates can be obtained from the following method: 1. Push method     A Callback URL can be provided as part of the Client information. This will allow the pandago API to push status updates to the Callback URL. 2. Pull method     A Get Order endpoint can be pulled periodically to get the latest status.  ### Available Statuses | Status | Description | | - | - | | NEW | Order has been created | | RECEIVED | We've accepted the order and will be assigning it to a courier | | WAITING_FOR_TRANSPORT | Looking for a courier to pick up and deliver the order | | ASSIGNED_TO_TRANSPORT | Assigning order to a courier | | COURIER_ACCEPTED_DELIVERY | Courier accepted to pick up and deliver the order | | NEAR_VENDOR | Courier is near the pick-up point | | PICKED_UP | Courier has picked up the order | | COURIER_LEFT_VENDOR | Courier has left from pick-up point | | NEAR_CUSTOMER | Courier is near the drop-off point | | DELIVERED | Courier has delivered the order | | DELAYED | Order delivery has been delayed and estimated delivery time has been updated | | CANCELLED | Order has been cancelled | *Note: Please accept other statuses that are not currently listed above, a new status might be introduce in the future*  ### Supported Payment Methods | Payment Method | Description | | - | - | | PAID | Order has been fully paid already and courier will not collect any amount from the end customer | | CASH_ON_DELIVERY | Courier will collect payment (order amount) from the end customer upon delivery | *Note: For the billing of the pandago delivery fee, this will be settled separately according to the agreed billing schedule and process*  # Getting Started These are the steps to start using the pandago API: 1. Generate Key Pair (Private Key and Public Key) to support secure communication with the pandago API.     Follow these commands on a terminal:    ```    # Generate private key    # output: client.pem file    openssl genrsa -out client.pem 2048     # Generate public key from the generated private one    # input: client.pem file    # output: client.pub file    openssl rsa -in client.pem -pubout > client.pub    ```    Or, follow these steps:    1. Open a browser and access this Online RSA Generator (https://cryptotools.net/rsagen)    2. Select key length to 2048 bit, and click the Generate Key Pair button.    3. Copy and save the Private Key to a file with .pem extension (e.g. client.pem).    4. Copy and save the Public Key to a file with .pub extension (e.g. client.pub). 2. Contact a pandago representative stating your interest in using the pandago API and provide the following information:    1. Public Key (file: client.pub)    2. Brand or Branch details:       | Attribute | Description | Example |       | - | - | - |       | Name | Customer known name for your Brand or Branch | Store ABC |       | Address | Address of your Brand or Branch. This address will be used as default sender. | 12 Street 3 unit #4-56 |       | Latitude | Latitude of the Address | 1.2867416 |       | Longitude | Longitude of the Address | 103.8523024 |       | Callback URL | [OPTIONAL] The Callback URL for pushing Order statuses must be an HTTPS with valid SSL certificate. See: Order Status Callback | https://example.io/update |  3. The pandago representative will provide you with ClientID, KeyID and Scope that your service will need to generate an Access Token for thepandago API.    | Attribute | Description | Example |    | - | - | - |    | ClientID | Your service identifier| pandago:sg:00000000-0000-0000-0000-000000000000 |    | KeyID | Your public key identifier| 00000000-0000-0000-0000-000000000001 |    | Scope | Access scope of your service| pandago.api.sg.* | 
 *
 * OpenAPI spec version: 1.1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.kalsym.pandago.client.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CreateOrUpdateOutletRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-23T10:38:41.071+05:00")
public class CreateOrUpdateOutletRequest {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("street")
  private String street = null;

  @JsonProperty("street_number")
  private String streetNumber = null;

  @JsonProperty("building")
  private String building = null;

  @JsonProperty("district")
  private String district = null;

  @JsonProperty("postal_code")
  private String postalCode = null;

  @JsonProperty("rider_instructions")
  private String riderInstructions = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("phone_number")
  private String phoneNumber = null;

  @JsonProperty("currency")
  private String currency = null;

  @JsonProperty("locale")
  private String locale = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("halal")
  private Boolean halal = null;

  public CreateOrUpdateOutletRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreateOrUpdateOutletRequest address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(required = true, value = "")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public CreateOrUpdateOutletRequest street(String street) {
    this.street = street;
    return this;
  }

   /**
   * Get street
   * @return street
  **/
  @ApiModelProperty(value = "")
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public CreateOrUpdateOutletRequest streetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

   /**
   * Get streetNumber
   * @return streetNumber
  **/
  @ApiModelProperty(value = "")
  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public CreateOrUpdateOutletRequest building(String building) {
    this.building = building;
    return this;
  }

   /**
   * Get building
   * @return building
  **/
  @ApiModelProperty(value = "")
  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public CreateOrUpdateOutletRequest district(String district) {
    this.district = district;
    return this;
  }

   /**
   * Get district
   * @return district
  **/
  @ApiModelProperty(value = "")
  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public CreateOrUpdateOutletRequest postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

   /**
   * Get postalCode
   * @return postalCode
  **/
  @ApiModelProperty(value = "")
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public CreateOrUpdateOutletRequest riderInstructions(String riderInstructions) {
    this.riderInstructions = riderInstructions;
    return this;
  }

   /**
   * Get riderInstructions
   * @return riderInstructions
  **/
  @ApiModelProperty(value = "")
  public String getRiderInstructions() {
    return riderInstructions;
  }

  public void setRiderInstructions(String riderInstructions) {
    this.riderInstructions = riderInstructions;
  }

  public CreateOrUpdateOutletRequest latitude(BigDecimal latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(required = true, value = "")
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public CreateOrUpdateOutletRequest longitude(BigDecimal longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Get longitude
   * @return longitude
  **/
  @ApiModelProperty(required = true, value = "")
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public CreateOrUpdateOutletRequest city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(required = true, value = "")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public CreateOrUpdateOutletRequest phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

   /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(required = true, value = "")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public CreateOrUpdateOutletRequest currency(String currency) {
    this.currency = currency;
    return this;
  }

   /**
   * Get currency
   * @return currency
  **/
  @ApiModelProperty(required = true, value = "")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public CreateOrUpdateOutletRequest locale(String locale) {
    this.locale = locale;
    return this;
  }

   /**
   * Get locale
   * @return locale
  **/
  @ApiModelProperty(required = true, value = "")
  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public CreateOrUpdateOutletRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CreateOrUpdateOutletRequest halal(Boolean halal) {
    this.halal = halal;
    return this;
  }

   /**
   * Get halal
   * @return halal
  **/
  @ApiModelProperty(value = "")
  public Boolean getHalal() {
    return halal;
  }

  public void setHalal(Boolean halal) {
    this.halal = halal;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateOrUpdateOutletRequest createOrUpdateOutletRequest = (CreateOrUpdateOutletRequest) o;
    return Objects.equals(this.name, createOrUpdateOutletRequest.name) &&
        Objects.equals(this.address, createOrUpdateOutletRequest.address) &&
        Objects.equals(this.street, createOrUpdateOutletRequest.street) &&
        Objects.equals(this.streetNumber, createOrUpdateOutletRequest.streetNumber) &&
        Objects.equals(this.building, createOrUpdateOutletRequest.building) &&
        Objects.equals(this.district, createOrUpdateOutletRequest.district) &&
        Objects.equals(this.postalCode, createOrUpdateOutletRequest.postalCode) &&
        Objects.equals(this.riderInstructions, createOrUpdateOutletRequest.riderInstructions) &&
        Objects.equals(this.latitude, createOrUpdateOutletRequest.latitude) &&
        Objects.equals(this.longitude, createOrUpdateOutletRequest.longitude) &&
        Objects.equals(this.city, createOrUpdateOutletRequest.city) &&
        Objects.equals(this.phoneNumber, createOrUpdateOutletRequest.phoneNumber) &&
        Objects.equals(this.currency, createOrUpdateOutletRequest.currency) &&
        Objects.equals(this.locale, createOrUpdateOutletRequest.locale) &&
        Objects.equals(this.description, createOrUpdateOutletRequest.description) &&
        Objects.equals(this.halal, createOrUpdateOutletRequest.halal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, street, streetNumber, building, district, postalCode, riderInstructions, latitude, longitude, city, phoneNumber, currency, locale, description, halal);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateOrUpdateOutletRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    streetNumber: ").append(toIndentedString(streetNumber)).append("\n");
    sb.append("    building: ").append(toIndentedString(building)).append("\n");
    sb.append("    district: ").append(toIndentedString(district)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    riderInstructions: ").append(toIndentedString(riderInstructions)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    halal: ").append(toIndentedString(halal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

