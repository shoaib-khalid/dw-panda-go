package com.kalsym.pandaGo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kalsym.pandaGo.ProcessResult;
import com.kalsym.pandaGo.deliveryservice.Location;
import com.kalsym.pandaGo.deliveryservice.PriceResult;
import com.kalsym.pandaGo.deliveryservice.SubmitOrderResult;
import com.kalsym.pandaGo.deliveryservice.models.RequestBodies.lalamoveGetPrice.Delivery;
import com.kalsym.pandaGo.deliveryservice.models.daos.DeliveryOrder;
import com.kalsym.pandaGo.utils.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger("PandaGo-application");


    public Object getPrice(String providerConfig, String orderObject, String systemTransactionId, String fulfillment) {
        logger.warn("Request Get Price [:{}], Request Body : {}", systemTransactionId, orderObject);
        JsonObject config = new Gson().fromJson(providerConfig, JsonObject.class);

        String getpriceUrl = config.get("getprice_url").getAsString();
        String loginUrl = config.get("loginUrl").getAsString();
        String queryAddressUrl = config.get("queryAddressUrl").getAsString();
        String key = config.get("key").getAsString();
        String username = config.get("username").getAsString();
        String password = config.get("password").getAsString();
        int connectTimeout = config.get("getprice_connect_timeout").getAsInt();
        int waitTimeout = config.get("getprice_wait_timeout").getAsInt();

        LogUtil.info(systemTransactionId, "Configuration : ", config.toString());


        JsonObject order = new Gson().fromJson(orderObject, JsonObject.class);
        JsonObject fulfillments = new Gson().fromJson(fulfillment, JsonObject.class);

        LogUtil.info(systemTransactionId, "Process start", "");
        ProcessResult response = new ProcessResult();

        LogUtil.info(systemTransactionId, "Request Url :" + getpriceUrl, "");

        String token = getToken(systemTransactionId, loginUrl, username, password, connectTimeout, waitTimeout);
        if (!token.isEmpty()) {
            JsonObject request = new JsonObject();
            JsonObject source = new JsonObject();
            JsonObject destination = new JsonObject();
            LogUtil.info(systemTransactionId, "Response : ", order.toString());

            Location query = getAddressId(systemTransactionId, order.getAsJsonObject("delivery").get("deliveryAddress").getAsString(), queryAddressUrl, key);
            if (query == null) {
                PriceResult result = new PriceResult();
                result.message = "ERR_OUT_OF_SERVICE_AREA";
                result.isError = true;
                response.returnObject = result;
                response.resultCode = -1;
                return response;
            }

            source.addProperty("latitude", order.getAsJsonObject("pickup").get("latitude").getAsBigDecimal());
            source.addProperty("longitude", order.getAsJsonObject("pickup").get("longitude").getAsBigDecimal());
            destination.addProperty("latitude", query.latitude);
            destination.addProperty("longitude", query.longitude);
//            source.addProperty("latitude", 33.686627);
//            source.addProperty("longitude", 73.005008);
//            destination.addProperty("longitude", 33.652903);
//            destination.addProperty("longitude", 73.056072);
            request.add("source", source);
            request.add("destination", destination);
            request.addProperty("vendor_id", "ba0u");
//            request.addProperty("vendor_id", order.getAsJsonObject("pickup").get("costCenterCode").getAsString());
//            request.addProperty("delivery_type", order.get("itemType").getAsString().toLowerCase(Locale.ROOT));
            request.addProperty("delivery_type", "food");
            String pickupTime = "";


            HashMap<String, String> httpHeader = new HashMap<>();
            httpHeader.put("Content-Type", "application/json");
            httpHeader.put("Authorization", "Bearer " + token);

            HttpResult httpResult = HttpsPostConn.SendHttpsRequest("POST", systemTransactionId, getpriceUrl, httpHeader, request.toString(), connectTimeout, waitTimeout);
            LogUtil.info(systemTransactionId, "Response : ", httpResult.responseString);

            if (httpResult.httpResponseCode == 200) {
                LogUtil.info(systemTransactionId, "Request successful", "");
                logger.warn("Response Get Price [:{}], Response Body : {}", systemTransactionId, httpResult.responseString);
                JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);

                PriceResult priceResult = new PriceResult();
                BigDecimal bd = BigDecimal.valueOf(jsonResp.get("fee").getAsDouble());
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                priceResult.price = bd;
                priceResult.pickupDateTime = pickupTime;
                priceResult.fulfillment = fulfillments.get("fulfillment").getAsString();
                priceResult.isError = false;
                priceResult.signature = jsonResp.get("signature").getAsString();
                logger.warn("Response Get signature [:{}], Response Body : {}", systemTransactionId, priceResult.signature);

                try {
                    if (fulfillments.get("interval") != null) {
                        priceResult.interval = fulfillments.get("interval").getAsInt();
                    } else {
                        priceResult.interval = null;
                    }
                } catch (Exception ex) {
                    System.err.println("Print Excep :  + " + ex.getMessage());
                    priceResult.interval = null;
                }
                response.returnObject = priceResult;

            } else {
                JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);
                PriceResult result = new PriceResult();
                LogUtil.info(systemTransactionId, "Request failed", jsonResp.get("message").getAsString());

                result.message = jsonResp.get("message").getAsString();
                result.isError = true;
                response.returnObject = result;
                response.resultCode = -1;
            }
            LogUtil.info(systemTransactionId, String.valueOf(httpResult.httpResponseCode), "");
        } else {
            PriceResult result = new PriceResult();
            LogUtil.info(systemTransactionId, "Get Token Failed", "", "");

            result.message = "Authentication Failed";
            result.isError = true;
            response.returnObject = result;
            response.resultCode = -1;
        }
        return new JSONObject(new Gson().toJson(response));
    }

    public Object placeOrder(String providerConfig, String orderObject, String systemTransactionId) {
        logger.warn("Request Place Order [:{}], Request Body : {}", systemTransactionId, orderObject);
        String location = "PandaGoSubmitOrder";
        ProcessResult response = new ProcessResult();
        String spOrderId;
//        String driverId;
//        String shareLink;
//        String status;

        LogUtil.info(systemTransactionId, location, "Process start", "");

        JsonObject config = new Gson().fromJson(providerConfig, JsonObject.class);


        String endpointUrl = config.get("place_orderUrl").getAsString();
        int connectTimeout = config.get("submitorder_connect_timeout").getAsInt();
        int waitTimeout = config.get("submitorder_wait_timeout").getAsInt();
        String loginUrl = config.get("loginUrl").getAsString();
        String username = config.get("username").getAsString();
        String password = config.get("password").getAsString();
        String token = getToken(systemTransactionId, loginUrl, username, password, connectTimeout, waitTimeout);

        JsonObject order = new Gson().fromJson(orderObject, JsonObject.class);

        List<Delivery> deliveries = new ArrayList<>();

        String pickupContactNO;
        String deliveryContactNo;
        pickupContactNO = order.getAsJsonObject("pickup").get("pickupContactPhone").getAsString();
        deliveryContactNo = order.getAsJsonObject("delivery").get("deliveryContactPhone").getAsString();
        LogUtil.info(systemTransactionId, location, "[" + systemTransactionId + "] Order GET Delivery:" + order.getAsJsonObject("delivery"), "");


        String deliveryContactName = order.getAsJsonObject("delivery").get("deliveryContactName").getAsString();
        JsonObject request = new JsonObject();
        JsonObject customer = new JsonObject();
        JsonObject orders = new JsonObject();
        JsonObject address = new JsonObject();
        address.addProperty("latitude", order.getAsJsonObject("delivery").get("latitude").getAsBigDecimal());
        address.addProperty("longitude", order.getAsJsonObject("delivery").get("longitude").getAsBigDecimal());
        address.addProperty("address", order.getAsJsonObject("delivery").get("deliveryAddress").getAsString());
        address.addProperty("notes", "");
        customer.addProperty("phone_number", deliveryContactNo);
        customer.addProperty("name", order.getAsJsonObject("delivery").get("deliveryContactName").getAsString());
        if (order.get("paymentType").getAsString().equals("COD")) {
            orders.addProperty("payment_method", "CASH_ON_DELIVERY");
            orders.addProperty("amount", order.get("orderAmount").getAsDouble()); //order fee
        }
        customer.add("location", address);
        orders.addProperty("description", "parcel");
        orders.addProperty("cold_bag_needed", false);
        request.addProperty("delivery_fee", String.valueOf(order.get("shipmentValue").getAsDouble()));
        request.addProperty("signature", order.get("signature").getAsString());
        request.addProperty("vendor_id", order.getAsJsonObject("pickup").get("costCenterCode").getAsString());
        request.add("order", orders);
        request.add("customer", customer);

        LogUtil.info(systemTransactionId, location, "BASEURL :" + endpointUrl + " ENDPOINT :" + endpointUrl, "" + request);

        JSONObject orderBody = new JSONObject(new Gson().toJson(request));

        HashMap<String, String> httpHeader = new HashMap<>();
        httpHeader.put("Content-Type", "application/json");
        httpHeader.put("Authorization", "Bearer " + token);

        try {
//            HttpResult httpResult = HttpsPostConn.SendHttpsRequest("POST", systemTransactionId, endpointUrl, httpHeader, orderBody.toString(), connectTimeout, waitTimeout);
//            int statusCode = httpResult.httpResponseCode;
            int statusCode = 200;
//            LogUtil.info(systemTransactionId, location, "Response Code : " + httpResult.toString());
            String r = "{\"response_code\":200,\"message\":\"Ok\",\"result\":{\"rps_order_id\":\"\",\"order_id\":\"ba0u-55bc15\",\"vendor_id\":\"ba0u\",\"delivery_fee\":\"120.0\",\"vendor\":{\"vendor_id\":\"ba0u\",\"vendor_information\":{\"vendor_id\":\"ba0u\",\"salesforce_id\":\"\",\"chain_id\":\"cb1qb\",\"chain_name\":\"ODR\",\"billing_parent_id\":\"\",\"global_vendor_id\":\"FP_PK\",\"name\":\"Kalsym System ODR\",\"names\":[{\"value\":\"Kalsym System ODR\",\"locale\":\"en-PK\"}],\"description\":\"\",\"descriptions\":null,\"global_entity_id\":\"ODR_PK\",\"creation_date\":\"2022-03-02T09:10:15Z\",\"business_type\":\"courier\",\"vertical_id\":\"\",\"vertical_type\":\"courier_business\",\"locale\":\"en-PK\",\"timestamp\":\"2022-04-05T07:09:46.027219095Z\",\"currency\":\"PKR\",\"active\":true,\"key_account\":false,\"halal\":false,\"test_vendor\":false,\"white_label_domain\":\"\",\"images_urls\":{\"logo\":\"\",\"header\":\"\"},\"vendor_detail_page_slug\":\"https://foodpanda.pk/restaurant/ba0u/kalsym-system-odr\",\"tags\":null,\"attributes\":[{\"external_id\":\"81600\",\"name\":\"1\",\"names\":null,\"type\":\"budget\"},{\"external_id\":\"77\",\"name\":\"Cakes \\u0026 Bakery\",\"names\":[{\"value\":\"Cakes \\u0026 Bakery\",\"locale\":\"en-PK\"}],\"type\":\"cuisine\"},{\"external_id\":\"77\",\"name\":\"Cakes \\u0026 Bakery\",\"names\":[{\"value\":\"Cakes \\u0026 Bakery\",\"locale\":\"en-PK\"}],\"type\":\"primary_cuisine\"},{\"external_id\":\"t9vz6wl\",\"name\":\"campaign:Pehla Order\",\"names\":[{\"value\":\"filter_only\",\"locale\":\"en-GB\"}],\"type\":\"tag\"}],\"delivery_types\":[\"platform_delivery\"],\"location\":{\"address_text\":\"I \\u0026 T Center, First Floor, Plot No. 22 \\u0026 23, G-8/4\",\"rider_instructions\":\"\",\"street\":\"I \\u0026 T Center, First Floor, Plot No. 22 \\u0026 23, G-8/4\",\"street_number\":\"\",\"building\":\"\",\"district\":\"\",\"postal_code\":\"44000\",\"city\":\"Islamabad\",\"country_code\":\"PK\",\"latitude\":33.69197,\"longitude\":73.05704},\"transmission\":{\"contact\":\"+92512331762\",\"locale\":\"en-PK\"},\"owners\":[\"mazhar@kalsym.com\"],\"contact\":{\"phone\":\"+92512331762\"},\"accepts_online_payment\":false,\"is_contracted\":false,\"timezone\":\"\",\"customer_types\":[\"b2c\",\"b2b\"],\"joker\":{\"minimum_promotion_definition_shift_enabled\":false}},\"vendor_fee\":\"\",\"is_active\":true,\"is_registered\":false,\"created_at\":1646212222,\"updated_at\":0,\"updated_by\":\"\",\"flags\":null},\"customer\":{\"phone_number\":\"+920192802728\",\"name\":\"Irasakumar\",\"address_raw\":null,\"location\":{\"address\":\"ST22, Upper Portion Of MCB Bank,44000,Islamabad,Federal\",\"latitude\":33.652903,\"longitude\":73.056072,\"notes\":\"\"}},\"order\":{\"payment_method\":\"CASH_ON_DELIVERY\",\"cold_bag_needed\":false,\"amount\":753.6,\"description\":\"parcel\"},\"logistics\":{\"driver_id\":\"\",\"driver_name\":\"\",\"driver_phone_number\":\"\",\"estimated_pickup_time\":\"\",\"estimated_delivery_time\":\"\",\"destination_latitude\":0,\"destination_longitude\":0},\"payment\":{\"transaction_id\":\"\",\"customer_type\":\"\",\"timestamp\":\"\",\"status\":\"\",\"wallet_id\":\"\",\"card_holder_name\":\"\",\"payment_method\":\"\",\"scheme\":\"\",\"masked_card_number\":\"\",\"card_valid_year\":0,\"card_valid_month\":0,\"amount\":0,\"email_address\":\"\"},\"cancellation_source\":\"\",\"purchase_id\":\"NON_PAYMENT\",\"status\":\"NEW\",\"reason\":\"\",\"ttl\":0,\"created_at\":1649400665,\"updated_at\":1649400665,\"strategy\":\"DYNAMIC\",\"order_origin\":\"pandago-web\",\"anonymize_partition_id\":0,\"payment_simulator\":false,\"rps_simulator\":false}}";
            LogUtil.info(systemTransactionId, location, "Response Code : " + r);

            if (statusCode == 200) {
                response.resultCode = 0;
//                JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);
                JsonObject jsonResp = new Gson().fromJson(r, JsonObject.class);
                JsonObject res = jsonResp.get("result").getAsJsonObject();
                LogUtil.info(systemTransactionId, location, "OrderNumber in process function:" + res.get("order_id").getAsString(), "");
                SubmitOrderResult submitOrderResult = new SubmitOrderResult();
                spOrderId = res.get("order_id").getAsString();
                String status = res.get("status").getAsString();


                DeliveryOrder orderCreated = new DeliveryOrder();
                orderCreated.setSpOrderId(spOrderId);
                orderCreated.setSpOrderName(spOrderId);
                orderCreated.setCreatedDate(DateTimeUtil.currentTimestamp());
//                orderCreated.setCustomerTrackingUrl(shareLink);
                orderCreated.setStatus(status);
                orderCreated.setStatusDescription(status);
                orderCreated.setSystemStatus("ASSIGNING_DRIVER");

                submitOrderResult.orderCreated = orderCreated;
                response.returnObject = submitOrderResult;
            } else {
//                JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);
                JsonObject jsonResp = new Gson().fromJson(r, JsonObject.class);
                LogUtil.info(systemTransactionId, location, "Response Code : " + jsonResp.get("message").getAsString());
                SubmitOrderResult submitOrderResult = new SubmitOrderResult();
                submitOrderResult.message = jsonResp.get("message").getAsString();
//                if (jsonResp.get("message").getAsString().equals("ERR_PRICE_MISMATCH")) {
//                    submitOrderResult.resultCode = 2;
//                } else if (jsonResp.get("message").getAsString().equals("ERR_OUT_OF_SERVICE_AREA")) {
                submitOrderResult.resultCode = -1;
//                } else {
//                    submitOrderResult.resultCode = -1;
//
//                }
                response.resultCode = -1;
                response.resultString = jsonResp.get("message").getAsString();
                response.returnObject = submitOrderResult;
            }

            LogUtil.info(systemTransactionId, location, "Process finish", "");
        } catch (Exception e) {
            response.resultCode = -1;
            SubmitOrderResult submitOrderResult = new SubmitOrderResult();
            submitOrderResult.resultCode = -1;
            response.returnObject = submitOrderResult;
            LogUtil.info(systemTransactionId, location, "Request failed", e.getMessage());

        }

        return new JSONObject(new Gson().toJson(response));
    }

    public String getToken(String systemTransactionId, String url, String username, String password, Integer
            connectTimeout, Integer waitTimeout) {
        String location = "PandaGoGetToken";
        LogUtil.info("Get Token : " + systemTransactionId, location, "", "");

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("email_address", username);
        jsonBody.addProperty("password", password);

        HashMap<String, String> httpHeader = new HashMap<>();
        httpHeader.put("Content-Type", "application/json");

        HttpResult httpResult = HttpsPostConn.SendHttpsRequest("POST", systemTransactionId, url, httpHeader, jsonBody.toString(), connectTimeout, waitTimeout);
        LogUtil.info(systemTransactionId, "Get Token Response : ", httpResult.responseString);

        JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);
        int statusCode = httpResult.httpResponseCode;
        if (statusCode == 200) {
            LogUtil.info(systemTransactionId, "Get Token Success : ", httpResult.responseString);

            return jsonResp.getAsJsonObject("result").get("ODRToken").getAsString();
        } else {
            LogUtil.info(systemTransactionId, "Get Token Failed : ", httpResult.responseString);
            return "";
        }
    }

    public Location getAddressId(String systemTransactionId, String address, String url, String key) {
        LogUtil.info(systemTransactionId, "Print Here  : ", address);

        HashMap<String, String> httpHeader = new HashMap<>();
        String add = address.replaceAll(" ", "+");

//        String requestUrl = url + add + "&key=" + key;
        String requestUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=St%23400,+G-8/4,44000,Islamabad,Federal%23400,+G-8/4,44000,Islamabad,Federal&key=AIzaSyCFhf1LxbPWNQSDmxpfQlx69agW-I-xBIw";
        HttpResult httpResult = HttpsGetConn.SendHttpsRequest("GET", systemTransactionId, requestUrl, httpHeader, 10000, 15000);
        LogUtil.info(systemTransactionId, "Get Cookie  : ", httpResult.responseString);
        Location location = new Location();
        if (httpResult.httpResponseCode == 200) {
            JsonObject jsonResp = new Gson().fromJson(httpResult.responseString, JsonObject.class);
            location.latitude = jsonResp.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsBigDecimal();
            location.longitude = jsonResp.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsBigDecimal();

        }

        return location;
    }

}
