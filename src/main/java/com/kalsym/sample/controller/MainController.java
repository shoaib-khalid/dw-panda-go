package com.kalsym.sample.controller;

import com.kalsym.pandago.client.api.LoginApi;
import com.kalsym.pandago.client.api.OutletsApi;
import com.kalsym.pandago.client.invoker.ApiClient;
import com.kalsym.pandago.client.model.AccessTokenResponse;
import com.kalsym.pandago.client.model.OutletListResponse;
import com.kalsym.sample.Main;
import com.kalsym.sample.utility.HttpResponse;
import com.kalsym.sample.utility.Logger;
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
    public ResponseEntity<HttpResponse> getAllOutlets(HttpServletRequest request,
                                                      @RequestParam(required = false, defaultValue = "") String token) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received getAllOutlets Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received getAllOutlets request");

        ApiClient apiClient = new ApiClient();

        String authorization = "Bearer " + token;
        final OutletsApi api = new OutletsApi();

        api.setApiClient(apiClient);
        OutletListResponse responseOutlet = api.outletListGet(authorization);

        return ResponseEntity.status(api.getApiClient().getStatusCode()).body(response);
    }

    @GetMapping(path = {"accessToken"}, name = "access-token-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('products-get', 'all')")
    public ResponseEntity<HttpResponse> accessToken(HttpServletRequest request,
                                                    @RequestParam(required = false, defaultValue = "true") boolean featured) {

        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received accessToken Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "received accessToken request");

        String basePath = "https://sts-st.deliveryhero.io";
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        final LoginApi api = new LoginApi();

        api.setApiClient(apiClient);
        AccessTokenResponse accTokenresponse = api.accessTokenGet();

        try {
            response.setData(accTokenresponse.toJSON());
            return ResponseEntity.status(api.getApiClient().getStatusCode()).body(response);
        } catch (Exception exp) {
            response.setMessage(exp.getMessage());
            return ResponseEntity.status(api.getApiClient().getStatusCode()).body(response);
        }


    }

    @GetMapping(path = {"login"}, name = "login-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('products-get', 'all')")
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
