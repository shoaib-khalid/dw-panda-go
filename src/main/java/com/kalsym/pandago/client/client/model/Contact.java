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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Please provide **&#x60;name&#x60;**, **&#x60;phone_number&#x60;** and **&#x60;location&#x60;** attributes  *NOTE for vendors in Finland, Norway, and Sweden:*  Vendors in the the named countries have the option to work with coordinates (by providing **&#x60;location.latitude&#x60;** and  **&#x60;location.longitude&#x60;**) or with addresses by providing **&#x60;location.address&#x60;**. Addresses should be specified in accordance with the format used by the national postal service of the specific country. Do not include additional address elements, like business names, floor numbers, etc. Delimit address elements by spaces. Examples:  * Jakobsbergsgatan 24 111 44 Stockholm  * Pasilankatu 10 00240 Helsinki  * Waldemar Thranes gate 98 0175 Oslo   &amp;nbsp;  *NOTE for vendors in Singapore:*  Vendors in Singapore have the option to work with location coordinates (by providing **&#x60;location.latitude&#x60;** and  **&#x60;location.longitude&#x60;**) or with postal codes (by providing **&#x60;location.postalcode&#x60;**). If both location coordinates and postal codes are provided, only the coordinates will be used for finding the exact location.  Postal code should be specified as a 6 digit number. For example: 752340. 
 */
@ApiModel(description = "Please provide **`name`**, **`phone_number`** and **`location`** attributes  *NOTE for vendors in Finland, Norway, and Sweden:*  Vendors in the the named countries have the option to work with coordinates (by providing **`location.latitude`** and  **`location.longitude`**) or with addresses by providing **`location.address`**. Addresses should be specified in accordance with the format used by the national postal service of the specific country. Do not include additional address elements, like business names, floor numbers, etc. Delimit address elements by spaces. Examples:  * Jakobsbergsgatan 24 111 44 Stockholm  * Pasilankatu 10 00240 Helsinki  * Waldemar Thranes gate 98 0175 Oslo   &nbsp;  *NOTE for vendors in Singapore:*  Vendors in Singapore have the option to work with location coordinates (by providing **`location.latitude`** and  **`location.longitude`**) or with postal codes (by providing **`location.postalcode`**). If both location coordinates and postal codes are provided, only the coordinates will be used for finding the exact location.  Postal code should be specified as a 6 digit number. For example: 752340. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-23T10:38:41.071+05:00")
public class Contact {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phone_number")
  private String phoneNumber = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("notes")
  private String notes = null;

  public Contact name(String name) {
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

  public Contact phoneNumber(String phoneNumber) {
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

  public Contact location(Location location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(required = true, value = "")
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Contact notes(String notes) {
    this.notes = notes;
    return this;
  }

   /**
   * Include instructions for our couriers regarding Pick-Up (in Sender) or Drop-Off (in Recipient). TIP: You can include your OrderID as part of Pick-Up instruction and our couriers will be able to see it in their app.
   * @return notes
  **/
  @ApiModelProperty(value = "Include instructions for our couriers regarding Pick-Up (in Sender) or Drop-Off (in Recipient). TIP: You can include your OrderID as part of Pick-Up instruction and our couriers will be able to see it in their app.")
  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.name, contact.name) &&
        Objects.equals(this.phoneNumber, contact.phoneNumber) &&
        Objects.equals(this.location, contact.location) &&
        Objects.equals(this.notes, contact.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, phoneNumber, location, notes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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

