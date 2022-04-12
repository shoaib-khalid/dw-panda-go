package com.kalsym.pandaGo.deliveryservice.models.daos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class RegionCountryState implements Serializable {
    @Id
    String id;
    String name;
    String regionCountryId;
}
