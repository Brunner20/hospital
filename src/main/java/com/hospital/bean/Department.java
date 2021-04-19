package com.hospital.bean;

import java.util.Arrays;

/**
 * The entity Department
 *
 * @author Anton Brunner
 */
public enum Department {

    SURGERY(1),
    THERAPY(2),
    GYNECOLOGY(3);

    private long id;

    Department(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static Department getDepartmentById(int id){
          return Arrays.stream(Department.values())
                .filter(x -> x.getId() == id)
                .findFirst()
                  .orElse(THERAPY);
    }
}
