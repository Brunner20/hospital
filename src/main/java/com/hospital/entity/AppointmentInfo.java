package com.hospital.entity;

import java.util.Objects;

public class AppointmentInfo {
    private long id;
    private String info;

    public AppointmentInfo(long id, String info) {
        this.id = id;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentInfo that = (AppointmentInfo) o;
        return id == that.id && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    @Override
    public String toString() {
        return "AppointmentInfo{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
