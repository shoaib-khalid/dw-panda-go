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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Order
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-23T10:38:41.071+05:00")
public class Order {
  @JsonProperty("order_id")
  private String orderId = null;

  @JsonProperty("client_order_id")
  private String clientOrderId = null;

  @JsonProperty("sender")
  private Sender sender = null;

  @JsonProperty("recipient")
  private Contact recipient = null;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod = null;

  @JsonProperty("coldbag_needed")
  private Boolean coldbagNeeded = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private Status status = null;

  @JsonProperty("delivery_fee")
  private BigDecimal deliveryFee = null;

  @JsonProperty("timeline")
  private Timeline timeline = null;

  @JsonProperty("driver")
  private Driver driver = null;

  @JsonProperty("created_at")
  private Integer createdAt = null;

  @JsonProperty("updated_at")
  private Integer updatedAt = null;

  @JsonProperty("tracking_link")
  private String trackingLink = null;

  @JsonProperty("proof_of_delivery_url")
  private String proofOfDeliveryUrl = null;

  @JsonProperty("cancellation")
  private Cancellation cancellation = null;

  @JsonProperty("delivery_tasks")
  private DeliveryTasks deliveryTasks = null;

  public Order orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Order clientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

   /**
   * Get clientOrderId
   * @return clientOrderId
  **/
  @ApiModelProperty(value = "")
  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public Order sender(Sender sender) {
    this.sender = sender;
    return this;
  }

   /**
   * Get sender
   * @return sender
  **/
  @ApiModelProperty(value = "")
  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public Order recipient(Contact recipient) {
    this.recipient = recipient;
    return this;
  }

   /**
   * Get recipient
   * @return recipient
  **/
  @ApiModelProperty(required = true, value = "")
  public Contact getRecipient() {
    return recipient;
  }

  public void setRecipient(Contact recipient) {
    this.recipient = recipient;
  }

  public Order paymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

   /**
   * Get paymentMethod
   * @return paymentMethod
  **/
  @ApiModelProperty(required = true, value = "")
  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Order coldbagNeeded(Boolean coldbagNeeded) {
    this.coldbagNeeded = coldbagNeeded;
    return this;
  }

   /**
   * Get coldbagNeeded
   * @return coldbagNeeded
  **/
  @ApiModelProperty(value = "")
  public Boolean getColdbagNeeded() {
    return coldbagNeeded;
  }

  public void setColdbagNeeded(Boolean coldbagNeeded) {
    this.coldbagNeeded = coldbagNeeded;
  }

  public Order amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Order description(String description) {
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

  public Order status(Status status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Order deliveryFee(BigDecimal deliveryFee) {
    this.deliveryFee = deliveryFee;
    return this;
  }

   /**
   * Get deliveryFee
   * @return deliveryFee
  **/
  @ApiModelProperty(required = true, value = "")
  public BigDecimal getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(BigDecimal deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public Order timeline(Timeline timeline) {
    this.timeline = timeline;
    return this;
  }

   /**
   * Get timeline
   * @return timeline
  **/
  @ApiModelProperty(required = true, value = "")
  public Timeline getTimeline() {
    return timeline;
  }

  public void setTimeline(Timeline timeline) {
    this.timeline = timeline;
  }

  public Order driver(Driver driver) {
    this.driver = driver;
    return this;
  }

   /**
   * Get driver
   * @return driver
  **/
  @ApiModelProperty(required = true, value = "")
  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public Order createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public Order updatedAt(Integer updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Get updatedAt
   * @return updatedAt
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Integer updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Order trackingLink(String trackingLink) {
    this.trackingLink = trackingLink;
    return this;
  }

   /**
   * Get trackingLink
   * @return trackingLink
  **/
  @ApiModelProperty(value = "")
  public String getTrackingLink() {
    return trackingLink;
  }

  public void setTrackingLink(String trackingLink) {
    this.trackingLink = trackingLink;
  }

  public Order proofOfDeliveryUrl(String proofOfDeliveryUrl) {
    this.proofOfDeliveryUrl = proofOfDeliveryUrl;
    return this;
  }

   /**
   * Get proofOfDeliveryUrl
   * @return proofOfDeliveryUrl
  **/
  @ApiModelProperty(value = "")
  public String getProofOfDeliveryUrl() {
    return proofOfDeliveryUrl;
  }

  public void setProofOfDeliveryUrl(String proofOfDeliveryUrl) {
    this.proofOfDeliveryUrl = proofOfDeliveryUrl;
  }

  public Order cancellation(Cancellation cancellation) {
    this.cancellation = cancellation;
    return this;
  }

   /**
   * Get cancellation
   * @return cancellation
  **/
  @ApiModelProperty(value = "")
  public Cancellation getCancellation() {
    return cancellation;
  }

  public void setCancellation(Cancellation cancellation) {
    this.cancellation = cancellation;
  }

  public Order deliveryTasks(DeliveryTasks deliveryTasks) {
    this.deliveryTasks = deliveryTasks;
    return this;
  }

   /**
   * Get deliveryTasks
   * @return deliveryTasks
  **/
  @ApiModelProperty(value = "")
  public DeliveryTasks getDeliveryTasks() {
    return deliveryTasks;
  }

  public void setDeliveryTasks(DeliveryTasks deliveryTasks) {
    this.deliveryTasks = deliveryTasks;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.orderId, order.orderId) &&
        Objects.equals(this.clientOrderId, order.clientOrderId) &&
        Objects.equals(this.sender, order.sender) &&
        Objects.equals(this.recipient, order.recipient) &&
        Objects.equals(this.paymentMethod, order.paymentMethod) &&
        Objects.equals(this.coldbagNeeded, order.coldbagNeeded) &&
        Objects.equals(this.amount, order.amount) &&
        Objects.equals(this.description, order.description) &&
        Objects.equals(this.status, order.status) &&
        Objects.equals(this.deliveryFee, order.deliveryFee) &&
        Objects.equals(this.timeline, order.timeline) &&
        Objects.equals(this.driver, order.driver) &&
        Objects.equals(this.createdAt, order.createdAt) &&
        Objects.equals(this.updatedAt, order.updatedAt) &&
        Objects.equals(this.trackingLink, order.trackingLink) &&
        Objects.equals(this.proofOfDeliveryUrl, order.proofOfDeliveryUrl) &&
        Objects.equals(this.cancellation, order.cancellation) &&
        Objects.equals(this.deliveryTasks, order.deliveryTasks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, clientOrderId, sender, recipient, paymentMethod, coldbagNeeded, amount, description, status, deliveryFee, timeline, driver, createdAt, updatedAt, trackingLink, proofOfDeliveryUrl, cancellation, deliveryTasks);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
    sb.append("    coldbagNeeded: ").append(toIndentedString(coldbagNeeded)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    deliveryFee: ").append(toIndentedString(deliveryFee)).append("\n");
    sb.append("    timeline: ").append(toIndentedString(timeline)).append("\n");
    sb.append("    driver: ").append(toIndentedString(driver)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    trackingLink: ").append(toIndentedString(trackingLink)).append("\n");
    sb.append("    proofOfDeliveryUrl: ").append(toIndentedString(proofOfDeliveryUrl)).append("\n");
    sb.append("    cancellation: ").append(toIndentedString(cancellation)).append("\n");
    sb.append("    deliveryTasks: ").append(toIndentedString(deliveryTasks)).append("\n");
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

