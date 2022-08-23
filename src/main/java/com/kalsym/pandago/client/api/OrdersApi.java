package com.kalsym.pandago.client.api;

import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.*;
import com.kalsym.pandago.client.model.*;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-23T10:38:41.071+05:00")
@Component("com.baeldung.petstore.client.api.OrdersApi")
public class OrdersApi {
    private ApiClient apiClient;

    public OrdersApi() {
        this(new ApiClient());
    }

    @Autowired
    public OrdersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Estimate Fee for an Order
     * Use this endpoint to estimate time of an order. It takes a JSON object containing an order. 
     * <p><b>200</b> - Success
     * <p><b>400</b> - Invalid request body
     * <p><b>422</b> - Unable to process request
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param body The body parameter
     * @return EstimateOrderFeeResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public EstimateOrderFeeResponse ordersFeePost(String authorization, EstimateOrderRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersFeePost");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling ordersFeePost");
        }
        
        String path = UriComponentsBuilder.fromPath("/orders/fee").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<EstimateOrderFeeResponse> returnType = new ParameterizedTypeReference<EstimateOrderFeeResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get Specific Order Current Courier Location
     * Use this endpoint to get current courier location. It takes a JSON object containing an order.
     * <p><b>200</b> - Success
     * <p><b>404</b> - Not found
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param orderId ID of the order to get
     * @return GetOrderCoordinateResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetOrderCoordinateResponse ordersOrderIdCoordinatesGet(String authorization, String orderId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersOrderIdCoordinatesGet");
        }
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'orderId' when calling ordersOrderIdCoordinatesGet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("order_id", orderId);
        String path = UriComponentsBuilder.fromPath("/orders/{order_id}/coordinates").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<GetOrderCoordinateResponse> returnType = new ParameterizedTypeReference<GetOrderCoordinateResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Cancel Specific Order
     * Use this endpoint to cancel an existing order. It takes a JSON object containing an order.  &gt; **IMPORTANT!** &gt;  * Starting **1 August 2021**, Cancellation reasons which are not from the acceptable reasons will be modified to &#x60;REASON_UNKNOWN&#x60; and responded with HTTP 203 instead of HTTP 204 &gt;  * Starting **1 September 2021**, Cancellation reason which are not from the acceptable reasons will be rejected.  *NOTE:*  *An order is only cancellable when it has not been accepted by a courier* 
     * <p><b>203</b> - Deprecated Success
     * <p><b>204</b> - Success
     * <p><b>404</b> - Not found
     * <p><b>409</b> - Uncancellable
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param orderId ID of the order to get
     * @param body The body parameter
     * @return ErrorResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ErrorResponse ordersOrderIdDelete(String authorization, String orderId, CancelOrderRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersOrderIdDelete");
        }
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'orderId' when calling ordersOrderIdDelete");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling ordersOrderIdDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("order_id", orderId);
        String path = UriComponentsBuilder.fromPath("/orders/{order_id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<ErrorResponse> returnType = new ParameterizedTypeReference<ErrorResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get Specific Order
     * Use this endpoint to get an existing order. It takes a JSON object containing an order. __NOTE: Cancellation object__ (seen at the end of the payload below) will be provided __only__ when the order is cancelled. It will not be available if the order is not cancelled.
     * <p><b>200</b> - Success
     * <p><b>404</b> - Not found
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param orderId ID of the order to get
     * @return GetOrderResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetOrderResponse ordersOrderIdGet(String authorization, String orderId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersOrderIdGet");
        }
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'orderId' when calling ordersOrderIdGet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("order_id", orderId);
        String path = UriComponentsBuilder.fromPath("/orders/{order_id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<GetOrderResponse> returnType = new ParameterizedTypeReference<GetOrderResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Submit a New Order
     * Use this endpoint to submit a new order. It takes a JSON object containing an order.
     * <p><b>201</b> - Success
     * <p><b>400</b> - Invalid request body
     * <p><b>401</b> - Unauthorized
     * <p><b>404</b> - Not found
     * <p><b>422</b> - Unable to process request - All different errors in an array
     * <p><b>500</b> - Server error - All different errors in an array
     * @param authorization The authorization parameter
     * @param body The body parameter
     * @return CreateOrderResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateOrderResponse ordersPost(String authorization, CreateOrderRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersPost");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling ordersPost");
        }
        
        String path = UriComponentsBuilder.fromPath("/orders").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<CreateOrderResponse> returnType = new ParameterizedTypeReference<CreateOrderResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get Proof of Delivery for Order
     * Use this endpoint to get proof of delivery for existing and delivered orders. __NOTE:__ Proof of delivery will be deleted after 1 week because of security and privacy concerns __NOTE:__ API returns encoded base64 file, so if you want to show that response in your html page, you should follow this element using &#x60;&lt;img src&#x3D;&#39;data:image/jpeg;base64,&lt;!-- Base64 data --&gt;&#39; /&gt;&#x60;
     * <p><b>200</b> - Success
     * <p><b>404</b> - Not found
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param orderId ID of the order to get its proof of delivery
     * @return GetProofOfDeliveryResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetProofOfDeliveryResponse ordersProofOfDeliveryOrderIdGet(String authorization, String orderId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersProofOfDeliveryOrderIdGet");
        }
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'orderId' when calling ordersProofOfDeliveryOrderIdGet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("order_id", orderId);
        String path = UriComponentsBuilder.fromPath("/orders/proof_of_delivery/{order_id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<GetProofOfDeliveryResponse> returnType = new ParameterizedTypeReference<GetProofOfDeliveryResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Estimate Time for an Order
     * Use this endpoint to estimate time of an order. It takes a JSON object containing an order.  *NOTE:*  *Time is estimated based on historical data and it does not consider live condition (e.g. traffic, weather, etc.)* 
     * <p><b>200</b> - Success
     * <p><b>400</b> - Invalid request body
     * <p><b>422</b> - Unable to process request
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param body The body parameter
     * @return EstimateOrderTimeResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public EstimateOrderTimeResponse ordersTimePost(String authorization, EstimateOrderRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling ordersTimePost");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling ordersTimePost");
        }
        
        String path = UriComponentsBuilder.fromPath("/orders/time").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        if (authorization != null)
        headerParams.add("Authorization", apiClient.parameterToString(authorization));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "Bearer Token" };

        ParameterizedTypeReference<EstimateOrderTimeResponse> returnType = new ParameterizedTypeReference<EstimateOrderTimeResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
