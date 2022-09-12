package com.kalsym.deliveryService.utility;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(
            HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {
        Logger.application.debug("Request body: {}", new String(reqBody, StandardCharsets.UTF_8));
        ClientHttpResponse response = ex.execute(req, reqBody);
        InputStreamReader isr = new InputStreamReader(
                response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr).lines()
                .collect(Collectors.joining("\n"));
        Logger.application.debug("Response body: {}", body);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        Logger.application.debug("request URI : " + request.getURI());
        Logger.application.debug("request method : " + request.getMethod());
        Logger.application.debug("request body : " + getRequestBody(body));
    }

    private String getRequestBody(byte[] body) throws UnsupportedEncodingException {
        if (body != null && body.length > 0) {
            return (new String(body, "UTF-8"));
        } else {
            return null;
        }
    }


    private void traceResponse(ClientHttpResponse response) throws IOException {
        String body = getBodyString(response);
        Logger.application.debug("response status code: " + response.getStatusCode());
        Logger.application.debug("response status text: " + response.getStatusText());
        Logger.application.debug("response body : " + body);
    }

    private String getBodyString(ClientHttpResponse response) {
        try {
            if (response != null && response.getBody() != null) {// &&
                // isReadableResponse(response))
                // {
                StringBuilder inputStringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                return inputStringBuilder.toString();
            } else {
                return null;
            }
        } catch (IOException e) {
            Logger.application.error(e.getMessage(), e);
            return null;
        }
    }

}
