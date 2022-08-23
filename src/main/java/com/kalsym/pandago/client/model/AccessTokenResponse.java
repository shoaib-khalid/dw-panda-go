package com.kalsym.pandago.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

}
