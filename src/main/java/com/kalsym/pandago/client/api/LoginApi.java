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
        formParams.add("client_id", "pandago:sg:349c98a1-92ef-43f2-b262-c1905cc133b9");
        formParams.add("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
        formParams.add("client_assertion", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjYyNDRmOTI4LWVmOTYtNGZhYi1hODA3LTc5MDM4YWQ5MzA0MiJ9.eyJpc3MiOiJwYW5kYWdvOnNnOjM0OWM5OGExLTkyZWYtNDNmMi1iMjYyLWMxOTA1Y2MxMzNiOSIsInN1YiI6InBhbmRhZ286c2c6MzQ5Yzk4YTEtOTJlZi00M2YyLWIyNjItYzE5MDVjYzEzM2I5IiwianRpIjoiYTg4MTE3ZDAtMWUxZC0xMWVkLTg2MWQtMDI0MmFjMTI4NjAyIiwiZXhwIjoxNjkwOTMyMDgyLCJhdWQiOiJodHRwczovL3N0cy5kZWxpdmVyeWhlcm8uaW8ifQ.ts1w86TPedFuAvIrhEVdzOWmUJ17gEktmuCohAfsRys47HDmsMn1-wHSzm8P09p1eZmYBgydHIpHasB1AT_PvXB2uxDUpfyFw1hBuldzBFi4YZNcSnyGnjnwv7YvmP8GRNQ8nrY1tBNkup83QxUq56qN8ow59FkdY-pGwBWLJbFksZ-BBLOCwG1lxAIYZ24vcVceNYu9hHCcuFrmUOVQeUuQ6RTJNUoMDuuLTrmpP3yf9t7OfsDsChDXTQM6GWxCAtRFtEHDbdIpYrT6IwKL8iVdYWmGK-LwurAe23plqwWKAkHzT2AkKQ_O3rHHykUPvBlDN8yJjrLL7ivMmKmtQA");
        formParams.add("scope", "pandago.api.sg.*");

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
