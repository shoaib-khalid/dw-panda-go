package com.kalsym.pandago.client.api;

import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.AccessTokenResponse;
import com.kalsym.pandago.client.model.CreateOrUpdateOutletRequest;
import com.kalsym.pandago.client.model.Outlet;
import com.kalsym.pandago.client.model.OutletListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginApi {

    private ApiClient apiClient;

    public LoginApi() {
        this(new ApiClient());
    }

    @Autowired
    public LoginApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Access Token
     * Kindly use this endpoint to get accessToken against the client id and other configurations. This end point will work only with Brand Vendors.
     * <p><b>200</b> - Success
     * <p><b>401</b> - Unauthorized
     * <p><b>500</b> - Server error
     *
     * @return OutletListResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessTokenResponse accessTokenGet() throws RestClientException {
        Object postBody = null;

        String path = UriComponentsBuilder.fromPath("/oauth2/token").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();

        headerParams.add("Content-Type", "application/x-www-form-urlencoded");

        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        formParams.add("grant_type", "client_credentials");
        formParams.add("client_id", "pandago:pk:d38a7884-f42d-49e8-989b-0efc93348f00");
        formParams.add("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
        formParams.add("client_assertion", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjBjODEwYTVkLTFjMzUtNGNhZS1iODhiLTZjYWUxZGUwODdhMyJ9.eyJpc3MiOiJwYW5kYWdvOnBrOmQzOGE3ODg0LWY0MmQtNDllOC05ODliLTBlZmM5MzM0OGYwMCIsInN1YiI6InBhbmRhZ286cGs6ZDM4YTc4ODQtZjQyZC00OWU4LTk4OWItMGVmYzkzMzQ4ZjAwIiwianRpIjoiMDU0ZjllNWMtNGFhZS0xMWVkLWI4NzgtMDI0MmFjMTIwMDAyIiwiZXhwIjoxNjk3MTcwNjk3LCJhdWQiOiJodHRwczovL3N0cy5kZWxpdmVyeWhlcm8uaW8ifQ.dqKJH8DFvkoWE23jS5WJeNXodPq7c7ikSPFwI3GQc7Ab6tljeGmtWOQX9SrZin-PESRFUsCGAf-tqY7ucYDpLtO9TyfRkc5vrKh0jNN61E1kKLOrWdL1ye9Cesf1bxVS2zwNW99Z1fHqF-eZhmFJFe7OnM243KPFqlJ_2HCUrQvYOGC9xq6gN0FQSvhCfvvSd-fKrExgJNIXS8CigMzaaS7fEKUkI5mR9MViFwfMgcLmUrWj_Bx9j7Yhoo9nrprdy7TmtrsDCcoYAd3ySBKjC3DD5I5kax7vpG6mKGtfVmUwwZj3NIe12M8coz39E4zlQyWD18zVlXXLLkvjdIOXjQ");
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

        ParameterizedTypeReference<AccessTokenResponse> accessTokenResponse = new ParameterizedTypeReference<AccessTokenResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, accessTokenResponse);
    }

}
