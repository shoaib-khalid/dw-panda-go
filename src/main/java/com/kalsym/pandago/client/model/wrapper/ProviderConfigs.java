package com.kalsym.pandago.client.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderConfigs {
    String authenticationUrl;
    String username;
    String password;
    String grantType;
    String client_id;
    String client_assertion_type;
    String client_assertion;
    String scope;
}
