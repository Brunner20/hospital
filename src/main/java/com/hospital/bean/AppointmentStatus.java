package com.hospital.bean;

import java.util.Arrays;


/**
 * The entity AppointmentStatus
 *
 * @author Anton Brunner
 */
public enum AppointmentStatus {

    APPOINTED(1),
    DONE(2),
    CANCELED(3);

    private long id;

    AppointmentStatus(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static AppointmentStatus getStatusById(int id){
        return Arrays.stream(AppointmentStatus.values())
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(APPOINTED);
    }
}
