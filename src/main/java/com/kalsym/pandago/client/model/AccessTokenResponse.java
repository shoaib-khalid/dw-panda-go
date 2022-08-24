package com.kalsym.pandago.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AccessTokenResponse {

    @JsonProperty("access_token")
    String accessToken = null;

    @JsonProperty("expires_in")
    String expiresIn = null;

    @JsonProperty("scope")
    String scope = null;

    @JsonProperty("token_type")
    String tokenType = null;

    public String toJSON() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            throw e;
        }
    }

}
