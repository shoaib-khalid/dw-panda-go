package com.kalsym.pandago.client.api;

import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.CallbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-23T10:38:41.071+05:00")
@Component("com.baeldung.petstore.client.api.CallbackApi")
public class CallbackApi {
    private ApiClient apiClient;

    public CallbackApi() {
        this(new ApiClient());
    }

    @Autowired
    public CallbackApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Calling your callback URL
     * **READ THIS FIRST!** * This is NOT an actual endpoint. This is a callback from pandago to your endpoint * This is used to represent the payload pandago will sent * This is an optional feature and it requires your callback URL to be registered with your ClientID  **Description** * The Callback URL will need to be HTTPS with a valid SSL certificate. * Body posted by this feature is stripped of any Personally Identifiable Information (PII). * Full Order body can be fetched using the Get Specific Order feature. * If there is an error, we retry sending the request three times. If the response status code is 2xx, it means that there was no error and the callback was successful in sending order status.  **Order Cancellation Callback** * When you cancel an order, if you provided a callback url, pandago will send request to that url. * In the payload, there will be Cancellation object which contains reason and source of cancellation * List of cancellation reasons are provided below.  * __NOTE: Cancellation object__ (seen at the end of the payload below) will be provided __only__ when the order is cancelled. It will not be available if the order is not cancelled.          | Cancellation Reason | Description | | - | - | | MISTAKE_ERROR | Incorrect order details  | | REASON_UNKNOWN | Customer cancelled order | | DELIVERY_ETA_TOO_LONG | Other delivery method became available | | UNABLE_TO_FIND | Rider was unable to find the customer  | | TECHNICAL_PROBLEM | System error / technical issue | | NO_COURIER | No riders were available  | | OUTSIDE_SERVICE_HOURS | Order was placed outside of service hours | | COURIER_ACCIDENT | Rider had an accident  | | BAD_WEATHER | Bad weather issue | | OUTSIDE_DELIVERY_AREA | Rider cannot deliver to the area  | | CLOSED | Restaurant was closed | | ADDRESS_INCOMPLETE_MISSTATED | Customer address was incorrect  | | ADDRESS_INCOMPLETE_MISSTATED | Customer address was incorrect  | | COURIER_UNREACHABLE | Rider was unreachable | | FOOD_QUALITY_SPILLAGE | Customer refused to accept order due to spillage | | LATE_DELIVERY | Customer refused to accept order due to late delivery | | UNABLE_TO_PAY | Customer was unable to pay for the order  | | WRONG_ORDER_ITEMS_DELIVERED | Customer refused to accept because items were incorrect | 
     * <p><b>200</b> - Success
     * @param body __NOTE: Cancellation object__ (seen at the end of the payload below) will be provided __only__ when the order is cancelled. It will not be available if the order is not cancelled.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void callbackPost(CallbackRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling callbackPost");
        }
        
        String path = UriComponentsBuilder.fromPath("/callback").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
