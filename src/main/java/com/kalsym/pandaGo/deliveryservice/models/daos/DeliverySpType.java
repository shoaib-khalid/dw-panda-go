package com.kalsym.pandaGo.deliveryservice.models.daos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliverySpType {
    @Id
    String id;

    Provider provider;
    String deliveryType;
    String regionCountry;
    String fulfilment;
    Integer interval;
}
