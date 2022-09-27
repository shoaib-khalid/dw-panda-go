package com.kalsym.deliveryService.controller;

import com.kalsym.pandago.client.api.LoginApi;
import com.kalsym.pandago.client.api.OutletsApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.AccessTokenResponse;
import com.kalsym.pandago.client.model.OutletListResponse;
import com.kalsym.deliveryService.Main;
import com.kalsym.deliveryService.utility.HttpResponse;
import com.kalsym.deliveryService.utility.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/")
public class MainController {

    @GetMapping(path = {"hello"}, name = "hello-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('products-get', 'all')")
    public ResponseEntity<HttpResponse> hello(HttpServletRequest request,
                                              @RequestParam(required = false, defaultValue = "true") boolean featured) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received Hello dw-panda-go Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received hello request");
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(path = {"getAllOutlets"}, name = "outlets-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('products-get', 'all')")
    public String getAllOutlets(HttpServletRequest request,
                                @RequestParam(required = false, defaultValue = "123") String token) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received getAllOutlets Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received getAllOutlets request");

        OutletListResponse outletListResponse = sendOutletListRequest(token);
        try {
            return outletListResponse.toJSON();
        } catch (Exception exp) {
            return "ERROR";
        }
        //return ResponseEntity.status(api.getApiClient().getStatusCode()).body(response);
    }

    @GetMapping(path = {"accessToken"}, name = "access-token-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('access-token-get', 'all')")
    public ResponseEntity<HttpResponse> accessToken(HttpServletRequest request) {

        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received accessToken Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received accessToken request");

        AccessTokenResponse accTokenresponse = sendAccessTokenRequest();

        try {
            response.setData(accTokenresponse.toJSON());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exp) {
            response.setMessage(exp.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public static OutletListResponse sendOutletListRequest(String token) {
        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OutletsApi api = new OutletsApi();

        api.setApiClient(apiClient);
        OutletListResponse outletListResponse = api.outletListGet(authorization);
        return outletListResponse;
    }

    public static AccessTokenResponse sendAccessTokenRequest() {

        String basePath = "https://sts.deliveryhero.io";
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        final LoginApi api = new LoginApi();

        api.setApiClient(apiClient);
        AccessTokenResponse accTokenresponse = api.accessTokenGet();

        return accTokenresponse;

    }

    @GetMapping(path = {"login"}, name = "login-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('login-get', 'all')")
    public String login(HttpServletRequest request,
                        @RequestParam(required = false, defaultValue = "true") boolean featured) {

        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received login Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received login request");

        String basePath = "https://sts-st.deliveryhero.io";
        String relativePath = "/oauth2/token";
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();

        headerParams.add("Content-Type", "application/x-www-form-urlencoded");

        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        formParams.add("grant_type", "client_credentials");
        formParams.add("client_id", "pandago:pk:d38a7884-f42d-49e8-989b-0efc93348f00");
        formParams.add("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
        formParams.add("client_assertion", "eyJhbGciOiJSUzI1NiIsImVudGl0bGVtZW50IjpmYWxzZX0.eyJpc3MiOiJEaW5vQ2hpZXNhLmdpdGh1Yi5pbyIsInN1YiI6InNoZW5pcXVhIiwiYXVkIjoiZXZhbmRlciIsImlhdCI6MTY2Mzc1Mzk1NCwiZXhwIjoxNjYzNzU0NTU0fQ.MlcEqyyXL8c4hKzMG0Oh0cnJkB4iax341HvwsRe7MOpN-hcCkHXFchOL_KXA4tm0x80dH916WuyOhBLF_pGW4UZQP02jrnGkcQwVnsBwtfcCZflMhz9y8OxQcr6JFFs_U5V3cbNYq2-PixNm5ZVtlETgdogpQqmhO5rjLhu6CGCYIW3ec99o8Yp3xMuNZvIfVemj0z2CCFmwHzKHxvEDb4EXyet0DWt1KVIuXUiTPSMMNIcxZqanHUc0wElbsknEr2KPuFz-hXVk1alabuli2SOAPKj7_X44onkKBObblPH0acDCAClVVPQs5IH2oIeww7jBqmBd5fi9udZoLrsVUw");
        formParams.add("scope", "pandago.api.pk.*");

        final String[] accepts = {
                "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{"Bearer Token"};

        Object postBody = null;
        ParameterizedTypeReference<AccessTokenResponse> accessTokenResponse = new ParameterizedTypeReference<AccessTokenResponse>() {
        };
        AccessTokenResponse accTokenResponse = null;
        accTokenResponse = apiClient.invokeAPI(relativePath, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, accessTokenResponse);

        try {
            return accTokenResponse.toJSON();
        } catch (Exception exp) {
            return "ERROR";
        }
    }

}
