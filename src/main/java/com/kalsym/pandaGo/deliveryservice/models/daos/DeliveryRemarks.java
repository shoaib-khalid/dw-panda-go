package com.kalsym.pandaGo.deliveryservice.models.daos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRemarks implements Serializable {
    @JsonIgnore
    @Id
    Long id;
    String title;
    String message;
    String deliveryType;
}
