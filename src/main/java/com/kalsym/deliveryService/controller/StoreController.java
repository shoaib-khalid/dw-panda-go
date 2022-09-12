package com.kalsym.deliveryService.controller;

import com.kalsym.pandago.client.api.OutletsApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.AccessTokenResponse;
import com.kalsym.pandago.client.model.CreateOrUpdateOutletRequest;
import com.kalsym.pandago.client.model.Outlet;
import com.kalsym.deliveryService.utility.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/store")
public class StoreController {

    /**
     * Edit or Add store
     * @param request
     * @param storeId
     * @param outletRequest
     * @return
     */
    @PutMapping(path = {"/{storeId}"}, name = "store-add-edit", produces = "application/json")
    public String store(HttpServletRequest request, @PathVariable String storeId, @RequestBody CreateOrUpdateOutletRequest outletRequest) {
        Logger.application.debug("Request addStore: ", outletRequest);
        return addOrUpdateStore(storeId, outletRequest);
    }

    private String addOrUpdateStore(String storeId, CreateOrUpdateOutletRequest outletRequest) {
        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Use token to getPrice
        ApiClient apiClient = new ApiClient();
        String token = accTokenresponse.getAccessToken();
        String authorization = "Bearer " + token;

        final OutletsApi api = new OutletsApi();
        api.setApiClient(apiClient);

        Outlet store = api.outletsClientVendorIdPut(authorization, storeId, outletRequest);
        try {
            Logger.application.debug("EstimateOrderFeeResponse: " + store.toJSON());
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }

        // 3. Return created/updated store
        try {
            return store.toJSON();
        } catch (Exception exp) {
            Logger.application.error("Exception ", exp);
            return "ERROR";
        }
    }

    @GetMapping(path={"/{storeId}"}, name="store-get", produces = "application/json")
    public String store(HttpServletRequest request, @PathVariable String storeId){
        // 1. Send accessToken request
        AccessTokenResponse accTokenresponse = MainController.sendAccessTokenRequest();
        Logger.application.info("AccessToken: [" + accTokenresponse.getAccessToken() + "]");

        // 2. Use token to getPrice
        ApiClient apiClient = new ApiClient();
        String token = accTokenresponse.getAccessToken();
        String authorization = "Bearer " + token;

        final OutletsApi api = new OutletsApi();
        api.setApiClient(apiClient);

        Outlet store = api.outletsClientVendorIdGet(authorization, storeId);
        try {
            Logger.application.debug("Store get response: " + store.toJSON());
        } catch (Exception exp) {
            Logger.application.error("Exception", exp);
        }

        // 3. Return the get store
        try {
            return store.toJSON();
        } catch (Exception exp) {
            Logger.application.error("Exception ", exp);
            return "ERROR";
        }
    }

}
