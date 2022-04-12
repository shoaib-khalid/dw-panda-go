package com.kalsym.pandaGo.deliveryservice.models.daos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryVehicleTypes {

    @Id
    public String vehicleType;
    public BigDecimal height;
    public BigDecimal width;
    public BigDecimal length;
    public BigDecimal weight;

}
