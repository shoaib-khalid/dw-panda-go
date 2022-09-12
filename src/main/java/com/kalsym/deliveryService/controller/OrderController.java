package com.kalsym.deliveryService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kalsym.deliveryService.Main;
import com.kalsym.deliveryService.model.*;
import com.kalsym.deliveryService.model.Enum.DeliveryCompletionStatus;
import com.kalsym.deliveryService.utility.DateTimeUtil;
import com.kalsym.pandago.client.api.OrdersApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.*;
import com.kalsym.deliveryService.utility.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import com.kalsym.pandago.client.model.wrapper.Order;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import com.google.gson.JsonObject;


@RestController()
@RequestMapping("/order")
public class OrderController {

    @GetMapping(path = {"/getPrice"}, name = "price-get", produces = "application/json")
    public String testPrice(HttpServletRequest request, @RequestBody String order) {
        Logger.application.info("testPrice [:{}], Request Body : {}", order);
        BigDecimal fee = (BigDecimal) GetPrice("", order, "", "false");
        return fee.toString();
    }


    @PostMapping(path = {"/placeOrder"}, name = "order-post", produces = "application/json")
    public String testOrder(HttpServletRequest request, @RequestBody Order order) {
        Logger.application.info("placeOrder request received");

        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Use token to placeOrder
        ProcessResult response = sendPlaceOrderRequest(accTokenresponse.getAccessToken(), order);
        try {
            Logger.application.info("Placed order: " + response.toString());
        } catch (Exception exp) {
            Logger.application.error("Exception ", exp);
        }

        // 3. Return price
        Logger.application.info("Returning placeOrder response");
        try {
            return response.toJSON();
        } catch (Exception exp) {
            Logger.application.error("Exception exp: ", exp);
            return "ERROR";
        }
    }


    @GetMapping(path = {"/orderStatus/{orderId}"}, name = "order-status", produces = "application/json")
    public String testOrderStatus(HttpServletRequest request, @PathVariable String orderId) {

        Logger.application.debug("OrderStatus, orderId: {}", orderId);
        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Get order status
        String token = accTokenresponse.getAccessToken();
        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OrdersApi api = new OrdersApi();

        api.setApiClient(apiClient);
        GetOrderResponse orderResponse = api.ordersOrderIdGet(authorization, orderId);
        try {
            return orderResponse.toJSON();
        } catch (Exception exp) {
            Logger.application.error("Exception in order status: ", exp);
            return "ERROR in order get";
        }
    }

    @DeleteMapping(path = {"/orderStatus"}, name = "order-del", produces = "application/json")
    public String delOrder(HttpServletRequest request, @RequestParam String orderId, @RequestParam String reason) {
        Logger.application.debug("del OrderStatus, orderId: {}", orderId);

        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Get order status
        String token = accTokenresponse.getAccessToken();
        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OrdersApi api = new OrdersApi();

        api.setApiClient(apiClient);
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        CancelOrderRequest.ReasonEnum reasonEnum = CancelOrderRequest.ReasonEnum.fromValue(reason);
        if (reasonEnum == null) {
            return "Wrong reason provided";
        }
        cancelOrderRequest.setReason(reasonEnum);
        ErrorResponse errorResponse = api.ordersOrderIdDelete(authorization, orderId, cancelOrderRequest);
        try {
            return errorResponse.toJSON();
        } catch (Exception exp) {
            Logger.application.error("Exception in order status: ", exp);
            return "ERROR in order delete";
        }
    }


    //@PreAuthorize("hasAnyAuthority('panda-go-price-get', 'all')")
    public Object GetPrice(
            @RequestParam(required = false, defaultValue = "") String providerConfig,
            @RequestParam(required = false, defaultValue = "") String order,
            @RequestParam(required = false, defaultValue = "123") String systemTransactionId,
            @RequestParam(required = false, defaultValue = "true") String fulfillment) {
        Logger.application.info(Logger.pattern, Main.VERSION, "GetPrice [" + systemTransactionId + "]", "Response : " + order);
        //TODO: Implement the following flow:
        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        JsonObject jsonResp = new Gson().fromJson(fulfillment, JsonObject.class);
        JsonObject orders = new Gson().fromJson(order, JsonObject.class);
        Logger.application.info("orders ::: [" + orders + "]");

        // 2. Use token to getPrice
        EstimateOrderFeeResponse orderRequest = sendOrderFeeRequest(accTokenresponse.getAccessToken(), orders.toString());
        PriceResult result = new PriceResult();
        result.setPrice(orderRequest.getEstimateDeliveryFee());
        result.setFulfillment(jsonResp.get("fulfillment").getAsString());
        result.setInterval(null);
        result.setQuotationId(null);
        result.setPickupDateTime(null);
        result.setLat(BigDecimal.valueOf(0.00));
        result.setLog(BigDecimal.valueOf(0.00));
        result.isError = false;
        ProcessResult response = new ProcessResult();
        response.setResultCode(0);
        response.setReturnObject(result);

        Logger.application.info("got price: " + response.toString());
        // 3. Return price
        Logger.application.info("Returning the price");
        return new JSONObject(new Gson().toJson(response));
    }

    public EstimateOrderFeeResponse sendOrderFeeRequest(String token, String orderObjectJSON) {

        Logger.application.info("Deserializing order from request");
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            order = objectMapper.readValue(orderObjectJSON, Order.class);
            Logger.application.info("ORDER : [" + order.toString() + "]");

        } catch (Exception exp) {
            Logger.application.error("Exception in mapping the Order class", exp);
        }
        EstimateOrderRequest orderRequest = new EstimateOrderRequest();

        orderRequest.clientOrderId(order.getOrderId());

        //////////////// SENDER  ///////////////////////////////////
        Sender sender = new Sender();
        sender.setName(order.getPickup().getPickupContactName());
        sender.setClientVendorId(order.getPickup().getCostCenterCode());

        if (order.getPickup().getPickupContactPhone().startsWith("+")) {
            sender.setPhoneNumber(order.getPickup().getPickupContactPhone());
        } else if (order.getPickup().getPickupContactPhone().startsWith("6")) {
            sender.setPhoneNumber("+" + order.getPickup().getPickupContactPhone());
        } else {
            sender.setPhoneNumber("+6" + order.getPickup().getPickupContactPhone());
        }
        Location senderLocation = new Location();
        String senderAddress = order.getDelivery().getDeliveryAddress();
        senderLocation.address(senderAddress);
        BigDecimal senderLatitude = order.getPickup().getLatitude();
        BigDecimal senderLongitude = order.getPickup().getLongitude();
        senderLocation.setLatitude(senderLatitude);
        senderLocation.setLongitude(senderLongitude);
        sender.setLocation(senderLocation);
        sender.setNotes("use the left side door");
        orderRequest.setSender(sender);

        ///////////////// recipient //////////////////////////////////
        ContactWithOptionalFields recipient = new ContactWithOptionalFields();
        recipient.setName(order.getDelivery().getDeliveryContactName());

        if (order.getDelivery().getDeliveryContactPhone().startsWith("+")) {
            recipient.setPhoneNumber(order.getDelivery().getDeliveryContactPhone());
        } else if (order.getDelivery().getDeliveryContactPhone().startsWith("6")) {
            recipient.setPhoneNumber("+" + order.getDelivery().getDeliveryContactPhone());
        } else {
            recipient.setPhoneNumber("+6" + order.getDelivery().getDeliveryContactPhone());
        }
        Location contactLocation = new Location();
        BigDecimal contactLatitude = order.getDelivery().getLatitude();
        BigDecimal contactLongitude = order.getDelivery().getLongitude();
        contactLocation.setLatitude(contactLatitude);
        contactLocation.setLongitude(contactLongitude);
        contactLocation.setAddress(order.getDelivery().getDeliveryAddress());
        recipient.setLocation(contactLocation);
        recipient.setNotes("use lift A and leave at the front door");
        orderRequest.setRecipient(recipient);
//        if (order.getPaymentType().equals("COD")) {
//            orderRequest.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
//            orderRequest.amount(BigDecimal.valueOf(order.getOrderAmount() + order.getShipmentValue()));
//
//        } else {
        orderRequest.setPaymentMethod(PaymentMethod.PAID);

//        }
        // Todo: Remove the hardcoded value
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
        // Todo: Need to modify according to the caller expectation.
        return estimatedFeeResponse;

    }


    public Object placeOrder(String providerConfig, String orderObject, String systemTransactionId) {
        Logger.application.info("placeOrder request received");

        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            order = objectMapper.readValue(orderObject, Order.class);
            Logger.application.info("ORDER : [" + order.toString() + "]");

        } catch (Exception exp) {
            Logger.application.error("Exception in mapping the Order class", exp);
        }

        // 2. Use token to placeOrder
        ProcessResult response = sendPlaceOrderRequest(accTokenresponse.getAccessToken(), order);
        try {
            Logger.application.info("Placed order: " + response.toString());
        } catch (Exception exp) {
            Logger.application.error("Exception ", exp);
        }

        // 3. Return price
        Logger.application.info("Returning placeOrder response");
        return new JSONObject(new Gson().toJson(response));
    }


    public ProcessResult sendPlaceOrderRequest(String token, Order createOrderRequest) {
        ProcessResult response = new ProcessResult();
        Logger.application.info("Deserializing order post request");

        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OrdersApi api = new OrdersApi();

        api.setApiClient(apiClient);

        try {
            Logger.application.debug("Sending OrderPostRequest: " + createOrderRequest);
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }

        CreateOrderResponse orderResponse = null;
        try {
            orderResponse = api.ordersPost(authorization, createOrderRequest);
            SubmitOrderResult submitOrderResult = new SubmitOrderResult();

            if (orderResponse != null) {
                DeliveryOrder deliveryOrderCreated = new DeliveryOrder();
                deliveryOrderCreated.setSpOrderId(orderResponse.getOrderId());
                deliveryOrderCreated.setStatus(orderResponse.getStatus());
                deliveryOrderCreated.setSpOrderName(orderResponse.getOrderId());
                deliveryOrderCreated.setVehicleType(createOrderRequest.getPickup().getVehicleType().name());
                deliveryOrderCreated.setCreatedDate(DateTimeUtil.currentTime());
                submitOrderResult.setOrderCreated(deliveryOrderCreated);
                submitOrderResult.setResultCode(0);
                response.setReturnObject(submitOrderResult);
                response.setResultCode(0);

                Logger.application.debug("EstimateOrderFeeResponse: " + orderResponse.toString());
            } else {
                submitOrderResult.setResultCode(-1);
                response.setReturnObject(submitOrderResult);
                response.setResultCode(-1);
            }
        } catch (Exception exp) {
            SubmitOrderResult submitOrderResult = new SubmitOrderResult();

            submitOrderResult.setResultCode(1);
            submitOrderResult.setMessage(exp.getMessage());
            response.setReturnObject(submitOrderResult);
            response.setResultCode(-1);
            Logger.application.error("Exception in EstimateOrderFeeResponse, ", exp);
        }
        return response;
    }


    public Object QueryOrder(String providerConfig, String spOrderId, String systemTransactionId) {


        Logger.application.info(" QueryOrder request received");

        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");


        // 2. Use token to placeOrder
        ProcessResult response = sendGetSpecificOrder(accTokenresponse.getAccessToken(), spOrderId);
        try {
            Logger.application.info("Query order: " + response.toString());
        } catch (Exception exp) {
            Logger.application.error("Exception ", exp);
        }

        // 3. Return price
        Logger.application.info("Returning placeOrder response");
        return new JSONObject(new Gson().toJson(response));
    }


    public ProcessResult sendGetSpecificOrder(String token, String orderId) {
        ProcessResult response = new ProcessResult();
        QueryOrderResult queryOrderResult = new QueryOrderResult();
        Logger.application.info("Deserializing order get request");

        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OrdersApi api = new OrdersApi();

        api.setApiClient(apiClient);

        try {
            Logger.application.debug("Sending GetSpecificOrder: " + orderId);
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }

        GetOrderResponse getOrderResponse = null;
        try {
            getOrderResponse = api.ordersOrderIdGet(authorization, orderId);
            Logger.application.info(Logger.pattern, Main.VERSION, "Order Controller", "Response : " + getOrderResponse.toJSON());


            if (getOrderResponse != null) {
                DeliveryOrder queryOrder = new DeliveryOrder();
                String status = getOrderResponse.getStatus();
                String driverId = getOrderResponse.getDriver().getId();
                queryOrderResult.setSuccess(true);

                queryOrder.setCustomerTrackingUrl(getOrderResponse.getTrackingLink());
                queryOrder.setSpOrderId(getOrderResponse.getOrderId());
                queryOrder.setStatus(status);

                switch (status) {
                    case "NEW":
                    case "RECEIVED":
                    case "WAITING_FOR_TRANSPORT":
                        queryOrder.setSystemStatus(DeliveryCompletionStatus.ASSIGNING_RIDER.name());
                        break;
                    case "ASSIGNED_TO_TRANSPORT":
                    case "COURIER_ACCEPTED_DELIVERY":
                    case "NEAR_VENDOR":
                        queryOrder.setDriverId(driverId);
                        queryOrder.setRiderName(getOrderResponse.getDriver().getName());
                        queryOrder.setRiderPhoneNo(getOrderResponse.getDriver().getPhoneNumber());
                        queryOrder.setSystemStatus(DeliveryCompletionStatus.AWAITING_PICKUP.name());
                        break;
                    case "PICKED_UP":
                    case "COURIER_LEFT_VENDOR":
                    case "NEAR_CUSTOMER":
                    case "DELAYED":
                        queryOrder.setDriverId(driverId);
                        queryOrder.setSystemStatus(DeliveryCompletionStatus.BEING_DELIVERED.name());
                        break;
                    case "DELIVERED":
                        queryOrder.setSystemStatus(DeliveryCompletionStatus.COMPLETED.name());
                        break;

                    case "CANCELLED":
                        queryOrder.setSystemStatus(DeliveryCompletionStatus.CANCELED.name());
                        break;
                }
                queryOrderResult.setOrderFound(queryOrder);
                response.setReturnObject(queryOrderResult);
                response.setResultCode(0);

                Logger.application.debug("GetSpecificOrder : " + getOrderResponse.toString());
            } else {
                queryOrderResult.isSuccess = false;
                response.resultCode = -1;
                response.returnObject = queryOrderResult;
                response.setReturnObject(queryOrderResult);
                response.setResultCode(-1);
            }
        } catch (Exception exp) {

            queryOrderResult.isSuccess = false;
            response.resultCode = -1;
            response.returnObject = queryOrderResult;
            response.setReturnObject(queryOrderResult);
            response.setResultCode(-1);

            Logger.application.error("Exception in GetSpecificOrder ", exp);
        }
        return response;
    }

    public Object ProviderCallback(String providerConfig, Object requestBody, String sysTransactionId) {
        ProcessResult response = new ProcessResult();
        ObjectMapper mapper = new ObjectMapper();
        CallbackRequest callbackRequest;

        try {
            callbackRequest = mapper.convertValue(requestBody, CallbackRequest.class);
            Logger.application.info(Logger.pattern, Main.VERSION, "SpCallbackResponse ", "Response : " + callbackRequest.toString());
            SpCallbackResult spCallbackResult = callback(callbackRequest);
            response.setReturnObject(spCallbackResult);
            response.setResultCode(0);

        } catch (Exception exception) {
            Logger.application.info(Logger.pattern, Main.VERSION, "SpCallbackResponse ", "Exception : " + exception.getMessage());
        }


        return new JSONObject(new Gson().toJson(response));
    }


    public SpCallbackResult callback(CallbackRequest request) {

        ProcessResult result = new ProcessResult();
        SpCallbackResult spCallbackResult = new SpCallbackResult();

        String status = request.getStatus().name();
        String spOrderId = request.getOrderId();
        String driverId = null;
        String systemStatus = null;

        String riderName = null;
        String riderPhone = null;
        String carNoPlate = null;
        String trackingLink = null;

        switch (status) {
            case "NEW":
            case "RECEIVED":
            case "WAITING_FOR_TRANSPORT":
                systemStatus = DeliveryCompletionStatus.ASSIGNING_RIDER.name();
                break;
            case "ASSIGNED_TO_TRANSPORT":
            case "COURIER_ACCEPTED_DELIVERY":
            case "NEAR_VENDOR":
                driverId = request.getDriver().getId();
                riderName = request.getDriver().getName();
                riderPhone = request.getDriver().getPhoneNumber();
                systemStatus = DeliveryCompletionStatus.AWAITING_PICKUP.name();
                break;
            case "PICKED_UP":
            case "COURIER_LEFT_VENDOR":
            case "NEAR_CUSTOMER":
            case "DELAYED":
                systemStatus = DeliveryCompletionStatus.BEING_DELIVERED.name();
                break;
            case "DELIVERED":
                systemStatus = DeliveryCompletionStatus.COMPLETED.name();
                break;

            case "CANCELLED":
                systemStatus = DeliveryCompletionStatus.CANCELED.name();
                break;
        }
        Logger.application.info(Logger.pattern, Main.VERSION, "SpCallbackResponse ", "driverId : " + driverId + " & riderName :" + riderName + " & riderPhone : " + riderPhone);


        spCallbackResult.setSpOrderId(spOrderId);
        spCallbackResult.setStatus(status);
        spCallbackResult.setDriverId(driverId);
        spCallbackResult.setSystemStatus(systemStatus);
        spCallbackResult.setTrackingUrl(trackingLink);
        spCallbackResult.setRiderName(riderName);
        spCallbackResult.setRiderPhone(riderPhone);
        spCallbackResult.setDriveNoPlate(carNoPlate);
        spCallbackResult.setResultCode(0);


        return spCallbackResult;
    }


//    @PostMapping(path = {"/addStore/{storeId}"}, name = "store-add", produces = "application/json")
//    public String addStore(HttpServletRequest request, @PathVariable String storeId, @RequestBody CreateOrUpdateOutletRequest outletRequest) {
//        Logger.application.debug("Request addStore: ", outletRequest);
//
//        return addOrUpdateStore(storeId, outletRequest);
//
//    }

}
