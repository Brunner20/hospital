package com.hospital.entity;

import java.util.Arrays;

public enum AppointmentType {

    PREPARATION(1),
    PROCEDURE(2),
    SURGERY(3);

    private long id;

    AppointmentType(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static AppointmentType getAppointmentTypeById(int id){
        return Arrays.stream(AppointmentType.values())
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
