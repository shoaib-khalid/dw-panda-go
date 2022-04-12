package com.kalsym.pandaGo.deliveryservice.models.RequestBodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Sarosh
 */
@ToString
@Getter
@Setter
public class UserAuthenticationBody {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
}
