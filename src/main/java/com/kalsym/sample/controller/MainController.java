package com.kalsym.sample.controller;

import com.kalsym.pandago.client.client.api.OutletsApi;
import com.kalsym.pandago.client.client.invoker.ApiClient;
import com.kalsym.pandago.client.client.model.Outlet;
import com.kalsym.pandago.client.client.model.OutletListResponse;
import com.kalsym.sample.Main;
import com.kalsym.sample.utility.HttpResponse;
import com.kalsym.sample.utility.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        Logger.application.info("Received Hello Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "recieveid hello request");
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(path = {"getAllOutlets"}, name = "outlets-get", produces = "application/json")
    //@PreAuthorize("hasAnyAuthority('products-get', 'all')")
    public ResponseEntity<HttpResponse> getAllOutlets(HttpServletRequest request,
                                              @RequestParam(required = false, defaultValue = "true") boolean featured) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Logger.application.info("Received getAllOutlets Request...");
        Logger.application.info(Logger.pattern, Main.VERSION, logprefix, "hello this is INFO log", "recieveid getAllOutlets request");


        ApiClient apiClient = new ApiClient();

//        String accessToken = "eyJhbGciOiJQUzI1NiIsImtpZCI6InNlY3VyaXR5LXN0YWdpbmctZXUtc3RzIiwidHlwIjoiSldUIn0.eyJhdWQiOlsiaHR0cHM6Ly9zdHMuZGVsaXZlcnloZXJvLmlvIl0sImV4cCI6MTY2MTI0MDg3MSwiaWF0IjoxNjYxMjM5OTcxLCJpc3MiOiJodHRwczovL3N0cy5kZWxpdmVyeWhlcm8uaW8iLCJqdGkiOiIyZTVhODAxZS1kNWI2LTRiMmItYTBkOS1kOWJmM2ZmZDliOGQiLCJuYmYiOjE2NjEyMzk5NzEsInNjcCI6WyJwYW5kYWdvLmFwaS5zZy4qIl0sInN1YiI6InBhbmRhZ286c2c6MzQ5Yzk4YTEtOTJlZi00M2YyLWIyNjItYzE5MDVjYzEzM2I5In0.KAgIiWeyG9v_mDDpktD32UUhpVbgZSzz3tpzgSdk6hzaJ0CX9tnIEXVv0js6fmxL_-ZAKJSEwL6xUvkN3KVUT3enfx6XY1uwgg-gXmvsfxnbGslFBbPjt32o2IL__QcChkg5QPgUvmLYUDEYLtKzpu725GdvCRellXuHDAINPylihggnqvuDFbE_LFaSPQjfJYDFH1L7rERwyxp2r_OxBnxj_uWedS5akWoEW0eBEVg9U2tashoK-u6-bqPGeDfNN_x2s7zK6PeGWHdMx9OOt0r-2uS4gB0KeR0J97pon5pP73UMkchtbrESSpK7hjXrsLWt0A5xOaSKi-4O672gKA";
//        apiClient.setAccessToken(accessToken);
//        String path = "/outletList";
//        HttpMethod getAllOutletList;

        // query params
//        List<Pair> queryParams = new ArrayList<Pair>();
//        Map<String, String> headerParams = new HashMap<String, String>();
//        Map<String, String> formParams = new HashMap<String, String>();

//        final String[] accepts = {
//                "application/json"
//        };
//        List<MediaType> accept = apiClient.selectHeaderAccept(accepts);


        //response = apiClient.invokeAPI(path,  HttpMethod.GET, null, null, null,null,accept,null,null,null);










        String authorization = "Bearer eyJhbGciOiJQUzI1NiIsImtpZCI6InNlY3VyaXR5LXN0YWdpbmctZXUtc3RzIiwidHlwIjoiSldUIn0.eyJhdWQiOlsiaHR0cHM6Ly9zdHMuZGVsaXZlcnloZXJvLmlvIl0sImV4cCI6MTY2MTI0OTMwMywiaWF0IjoxNjYxMjQ4NDAzLCJpc3MiOiJodHRwczovL3N0cy5kZWxpdmVyeWhlcm8uaW8iLCJqdGkiOiI4NTZmYWM2Ni1jODUzLTQwOTAtYTgyZi01ZGJkNDg3NzQ5N2EiLCJuYmYiOjE2NjEyNDg0MDMsInNjcCI6WyJwYW5kYWdvLmFwaS5zZy4qIl0sInN1YiI6InBhbmRhZ286c2c6MzQ5Yzk4YTEtOTJlZi00M2YyLWIyNjItYzE5MDVjYzEzM2I5In0.SS_b23j4qACAB30bTAx3Eo0VPLNNG2dbbJ-7rm1zc_gdY-YxgtrLq_ipeSbU0FA--kgxmWBwtxZiKdf9y-2WUrMXLzMtVACbviNBY5KTIBjCTBfus4PVdoU2PLI0BTCCN0rfjRqzq8jhYoNR6yelQByeQTqThf2Eg5XnqxeOOqVZ95mSJfgZHzE-pvpzXGP0yGTOdKeeS6sDX_S1ICQa50gqZqQXM2OgifaD-bvFzyjAEnu5PIq_oF1C8qJAvg8z_UegqFhAaw0s_8D3JMfyDwbQPJs4LR0cLRnL7JgCm6Utx6BwhrU10SGtRBZAP2uBnSuKWJ4Vqx201Hy4-uCVfg";
        String clientVendorId = null;
        final OutletsApi api = new OutletsApi();
        api.setApiClient(apiClient);
        OutletListResponse responseOutlet = api.outletListGet(authorization);













//        response.setStatus(HttpStatus.OK);
//
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
