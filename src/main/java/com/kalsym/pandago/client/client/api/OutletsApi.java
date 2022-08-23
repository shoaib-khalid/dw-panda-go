package com.kalsym.pandago.client.client.api;

import com.kalsym.pandago.client.client.invoker.ApiClient;
import com.kalsym.pandago.client.client.model.CreateOrUpdateOutletRequest;
import com.kalsym.pandago.client.client.model.Outlet;
import com.kalsym.pandago.client.client.model.OutletListResponse;
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
@Component("com.baeldung.petstore.client.api.OutletsApi")
public class OutletsApi {
    private ApiClient apiClient;

    public OutletsApi() {
        this(new ApiClient());
    }

    @Autowired
    public OutletsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get All Outlets
     * Kindly use this endpoint to get all outlets created under a single parent vendor. This end point will work only with Brand Vendors. When any Outlet vendor sends this request, they will see an error message saying \&quot;This vendor is not a parent\&quot;. 
     * <p><b>200</b> - Success
     * <p><b>401</b> - Unauthorized
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @return OutletListResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OutletListResponse outletListGet(String authorization) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling outletListGet");
        }
        
        String path = UriComponentsBuilder.fromPath("/outletList").build().toUriString();
        
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

        ParameterizedTypeReference<OutletListResponse> returnType = new ParameterizedTypeReference<OutletListResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get an Outlet
     * 
     * <p><b>200</b> - Success
     * <p><b>404</b> - Not found
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param clientVendorId ID of the outlet to get
     * @return Outlet
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Outlet outletsClientVendorIdGet(String authorization, String clientVendorId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling outletsClientVendorIdGet");
        }
        
        // verify the required parameter 'clientVendorId' is set
        if (clientVendorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clientVendorId' when calling outletsClientVendorIdGet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("client_vendor_id", clientVendorId);
        String path = UriComponentsBuilder.fromPath("/outlets/{client_vendor_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<Outlet> returnType = new ParameterizedTypeReference<Outlet>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create or Update an Outlet
     * *NOTE for vendors in Finland, Norway, and Sweden:*  Vendors in the the named countries have the option to work with coordinates (by providing **&#x60;latitude&#x60;** and  **&#x60;longitude&#x60;**) or with addresses by providing **&#x60;address&#x60;**. Addresses should be specified in accordance with the format used by the national postal service of the specific country. Do not include additional address elements, like business names, floor numbers, etc. Delimit address elements by spaces. Examples:  * Jakobsbergsgatan 24 111 44 Stockholm  * Pasilankatu 10 00240 Helsinki  * Waldemar Thranes gate 98 0175 Oslo  *NOTE for vendors in Singapore:*  Vendors in Singapore have the option to work with location coordinates (by providing **&#x60;latitude&#x60;** and  **&#x60;longitude&#x60;**) or with postal codes (by providing **&#x60;postal_code&#x60;**). If both location coordinates and postal codes are provided, only the coordinates will be used for finding the exact location.  Postal code should be specified as a 6 digit number. For example: 752340. 
     * <p><b>200</b> - Success
     * <p><b>400</b> - Invalid request body
     * <p><b>500</b> - Server error
     * @param authorization The authorization parameter
     * @param clientVendorId ID of the outlet to get
     * @param body The body parameter
     * @return Outlet
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Outlet outletsClientVendorIdPut(String authorization, String clientVendorId, CreateOrUpdateOutletRequest body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'authorization' when calling outletsClientVendorIdPut");
        }
        
        // verify the required parameter 'clientVendorId' is set
        if (clientVendorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clientVendorId' when calling outletsClientVendorIdPut");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling outletsClientVendorIdPut");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("client_vendor_id", clientVendorId);
        String path = UriComponentsBuilder.fromPath("/outlets/{client_vendor_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<Outlet> returnType = new ParameterizedTypeReference<Outlet>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
