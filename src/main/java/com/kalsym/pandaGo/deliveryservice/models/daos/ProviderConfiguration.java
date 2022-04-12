/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandaGo.deliveryservice.models.daos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author user
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderConfiguration implements Serializable {
    ProviderConfigurationId id;
    String configValue;
    Integer spId;
}
