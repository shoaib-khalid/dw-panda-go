/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandaGo.deliveryservice.models.daos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author user
 */
@Getter
@Setter
public class ProviderConfigurationId implements Serializable {
    Integer spId;
    String configField;
}
