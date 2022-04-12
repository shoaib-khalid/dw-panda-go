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
public class DeliveryServiceCharge implements Serializable {

    @Id
    private Long id;
    private String startTime;
    private String endTime;
    private String deliverySpId;
    private BigDecimal serviceFee;
}
