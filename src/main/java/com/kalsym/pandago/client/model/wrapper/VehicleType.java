package com.kalsym.pandago.client.model.wrapper;

public enum VehicleType {
    MOTORCYCLE(1),
    CAR(2),
    VAN(3),
    TRUCK(4);

    final public int number;

    VehicleType(final int number) {
        this.number = number;
    }
}
