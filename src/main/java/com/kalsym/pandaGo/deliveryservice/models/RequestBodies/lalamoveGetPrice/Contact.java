package com.kalsym.pandaGo.deliveryservice.models.RequestBodies.lalamoveGetPrice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contact {
    public String name;
    public String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
