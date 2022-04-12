/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package com.kalsym.pandaGo.deliveryservice.models.daos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author user
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    @Id
    String product_code;
    String description;

}
