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


package com.kalsym.pandago.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Acceptable Reasons:   * &#x60;ADDRESS_INCOMPLETE_MISSTATED&#x60; - Customer&#39;s address is incomplete OR customer enters incorrect address on purpose in order to be able to proceed with the order with the vendor, with the actual customer address outside the vendor&#39;s delivery area.   * &#x60;BAD_WEATHER&#x60; - Vendor/Logistics cannot deliver because of weather conditions.   * &#x60;CLOSED&#x60; - Vendor is closed.   * &#x60;COURIER_ACCIDENT&#x60; - Courier has been involved in an accident and cannot fulfill the order.   * &#x60;COURIER_UNREACHABLE&#x60; - Courier is unreachable/unresponsive and/or uncontactable.   * &#x60;DELIVERY_ETA_TOO_LONG&#x60; - Before promised delivery time, customer believes that the ETA is too long.   * &#x60;DUPLICATE_ORDER&#x60; - Duplicate order.   * &#x60;FOOD_QUALITY_SPILLAGE&#x60; - Customer has an issue with the food quality (cold, inedible, etc.) OR order spillage occurred during transport (VD/OD).   * &#x60;LATE_DELIVERY&#x60; - Customer has received delivery but (excessively) passed promised delivery time.   * &#x60;MISTAKE_ERROR&#x60; - Customer placed order in error/accidentally or with incorrect specifications (i.e. preorder, incorrect payment type).   * &#x60;NO_COURIER&#x60; - Vendor: Vendor has no courier (drivers/riders/walkers/etc.) available to fulfill the order. Logistics: Order is pending in Logistics/Hurrier without assigned courier and customer no longer wishes to wait.   * &#x60;OUTSIDE_DELIVERY_AREA&#x60; - Vendor: Vendor does not deliver to the customer&#39;s address/area. Logistics: Logistics does not deliver to the customer&#39;s address/area.   * &#x60;OUTSIDE_SERVICE_HOURS&#x60; - Order has been placed outside of our Logistic service hours.   * &#x60;REASON_UNKNOWN&#x60; - Reason for failure is not available. VENDOR: Vendor initiated, CUSTOMER: Customer initiated, PLATFORM: Unknown who initiated.   * &#x60;TECHNICAL_PROBLEM&#x60; - Vendor: Vendor cannot fulfill an order due to technical issues, e.g. order can not be printed, etc. Where a vendor cannot be contacted and order could not be delivered to their transmission/reception device or order was delivered to the device and it timed-out/expired, reasons UNREACHABLE and NO_RESPONSE should be used respectively rather than TECHNICAL_PROBLEM. Logistics: Logistics is having general technical issues (i.e. Hurrier is down, etc.). Platform: Platform is experiencing some technical issue whereby orders cannot be placed with the Logistics/Vendor and/or have been pending too long. As a result, orders have been failed by the platform.   * &#x60;UNABLE_TO_FIND&#x60; - Customer cannot be located to complete pick-up/delivery or order items.   * &#x60;UNABLE_TO_PAY&#x60; - Customer cannot pay for the order, e.g. insufficient cash (COD) or card doesn&#39;t work for card on delivery, etc.   * &#x60;WRONG_ORDER_ITEMS_DELIVERED&#x60; - Customer has received wrong order items or is missing significant item(s).
 */
public enum Reason {
  
  ADDRESS_INCOMPLETE_MISSTATED("ADDRESS_INCOMPLETE_MISSTATED"),
  
  BAD_WEATHER("BAD_WEATHER"),
  
  CLOSED("CLOSED"),
  
  COURIER_ACCIDENT("COURIER_ACCIDENT"),
  
  COURIER_UNREACHABLE("COURIER_UNREACHABLE"),
  
  DELIVERY_ETA_TOO_LONG("DELIVERY_ETA_TOO_LONG"),
  
  DUPLICATE_ORDER("DUPLICATE_ORDER"),
  
  FOOD_QUALITY_SPILLAGE("FOOD_QUALITY_SPILLAGE"),
  
  LATE_DELIVERY("LATE_DELIVERY"),
  
  MISTAKE_ERROR("MISTAKE_ERROR"),
  
  NO_COURIER("NO_COURIER"),
  
  OUTSIDE_DELIVERY_AREA("OUTSIDE_DELIVERY_AREA"),
  
  OUTSIDE_SERVICE_HOURS("OUTSIDE_SERVICE_HOURS"),
  
  REASON_UNKNOWN("REASON_UNKNOWN"),
  
  TECHNICAL_PROBLEM("TECHNICAL_PROBLEM"),
  
  UNABLE_TO_FIND("UNABLE_TO_FIND"),
  
  UNABLE_TO_PAY("UNABLE_TO_PAY"),
  
  WRONG_ORDER_ITEMS_DELIVERED("WRONG_ORDER_ITEMS_DELIVERED");

  private String value;

  Reason(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Reason fromValue(String text) {
    for (Reason b : Reason.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

