package com.kalsym.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalsym.pandago.client.api.OrdersApi;
import com.kalsym.pandago.client.api.OutletsApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.*;
import com.kalsym.sample.utility.Logger;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;
import com.kalsym.pandago.client.model.wrapper.Order;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController()
@RequestMapping("/order")
public class OrderController {

    @GetMapping(path = {"/getPrice"}, name = "price-get", produces = "application/json")
    public String testPrice(HttpServletRequest request, @RequestBody String order) {
        Logger.application.info("testPrice [:{}], Request Body : {}", order);
        getPrice("", order, "", "false");
        return "SUCCESS";
    }


    //@PreAuthorize("hasAnyAuthority('panda-go-price-get', 'all')")
    public Object getPrice(
            @RequestParam(required = false, defaultValue = "") String providerConfig,
            @RequestParam(required = false, defaultValue = "") String order,
            @RequestParam(required = false, defaultValue = "123") String systemTransactionId,
            @RequestParam(required = false, defaultValue = "true") String fulfillment) {
        Logger.application.debug("Request Get Price [:{}], Request Body : {}", systemTransactionId, order);
        //TODO: Implement the following flow:
        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Use token to getPrice
        EstimateOrderFeeResponse orderRequest = sendOrderFeeRequest(accTokenresponse.getAccessToken(), order);
        Logger.application.info("got price: " + orderRequest.getEstimateDeliveryFee());
        // 3. Return price
        Logger.application.info("Returning the price");
        return orderRequest.getEstimateDeliveryFee();
    }

    public EstimateOrderFeeResponse sendOrderFeeRequest(String token, String orderObjectJSON) {

        Logger.application.info("Deseriallizing order from request");
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            order = objectMapper.readValue(orderObjectJSON, Order.class);
        } catch (Exception exp) {
            Logger.application.error("Exception in mapping the Order class", exp);
        }
        EstimateOrderRequest orderRequest = new EstimateOrderRequest();

        orderRequest.clientOrderId(order.getOrderId());

        //////////////// SENDER  ///////////////////////////////////
        Sender sender = new Sender();
        // Todo: Remove the hardcoded value
        sender.setName("Pandago");
        sender.setClientVendorId("kalsymHQ");
        // Todo: Remove the hardcoded value
        sender.setPhoneNumber("+60133309331");
        Location senderLocation = new Location();
        String senderAddress = order.getDelivery().getDeliveryAddress();
        senderLocation.address(senderAddress);
        // Todo: Remove the hardcoded value
        BigDecimal senderLatitude = new BigDecimal(33.69217544186331);
        // Todo: Remove the hardcoded value
        BigDecimal senderLongitude = new BigDecimal(73.05701449740363);
        senderLocation.setLatitude(senderLatitude);
        senderLocation.setLongitude(senderLongitude);
        sender.setLocation(senderLocation);
        // Todo: Remove the hardcoded value
        sender.setNotes("use the left side door");
        orderRequest.setSender(sender);

        ///////////////// recipient //////////////////////////////////
        ContactWithOptionalFields recipient = new ContactWithOptionalFields();
        // Todo: Remove the hardcoded value
        recipient.setName("Kamran Khan");
        // Todo: Remove the hardcoded value
        recipient.setPhoneNumber("+60133309331");
        Location contactLocation = new Location();
        // Todo: Remove the hardcoded value
        BigDecimal contactLatitude = new BigDecimal(33.6851230498744);
        // Todo: Remove the hardcoded value
        BigDecimal contactLongitude = new BigDecimal(73.0435068932674);
        contactLocation.setLatitude(contactLatitude);
        contactLocation.setLongitude(contactLongitude);
        // Todo: Remove the hardcoded value
        contactLocation.setAddress("Butt Karahi Tikka");
        recipient.setLocation(contactLocation);
        // Todo: Remove the hardcoded value
        recipient.setNotes("use lift A and leave at the front door");
        orderRequest.setRecipient(recipient);
        // Todo: Remove the hardcoded value
        orderRequest.setPaymentMethod(PaymentMethod.PAID);
        // Todo: Remove the hardcoded value
        orderRequest.amount(BigDecimal.valueOf(250.34));
        orderRequest.coldbagNeeded(false);
        // Todo: Remove the hardcoded value
        orderRequest.setDescription("Refreshing drink");

        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OrdersApi api = new OrdersApi();

        api.setApiClient(apiClient);

        try {
            Logger.application.debug("Sending OrderRequest: " + orderRequest.toJSON());
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }

        EstimateOrderFeeResponse estimatedFeeResponse = api.ordersFeePost(authorization, orderRequest);
        try {
            Logger.application.debug("EstimateOrderFeeResponse: " + estimatedFeeResponse.toJSON());
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }
        return estimatedFeeResponse;

    }

    public Object placeOrder(String providerConfig, String orderObject, String systemTransactionId) {
        return null;
    }

    public String getToken(String systemTransactionId, String url, String username, String password, Integer
            connectTimeout, Integer waitTimeout) {
        return null;
    }

    public Object getAddressId(String systemTransactionId, String address, String url, String key) {
        return null;
    }


}
