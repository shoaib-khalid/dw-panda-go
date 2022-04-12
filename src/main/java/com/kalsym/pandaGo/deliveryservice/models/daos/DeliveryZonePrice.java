package com.kalsym.pandaGo.deliveryservice.models.daos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryZonePrice implements Serializable {

    @Id
    private Long id;
    private String spId;
    private double weight;
    private BigDecimal withInCity;
    private BigDecimal sameZone;
    private BigDecimal differentZone;
}


