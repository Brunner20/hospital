package com.hospital.entity;

import java.util.Objects;

public class Staff implements Visitor {

    private long id;
    private String firstname;
    private String Lastname;
    private String picture;
    private long StaffTypeID;
    private long departmentID;
    private long accountID;

    public Staff() {
    }

    public Staff(long id, String firstname, String lastname, String picture, long staffTypeID, long departmentID, long accountID) {
        this.id = id;
        this.firstname = firstname;
        Lastname = lastname;
        this.picture = picture;
        StaffTypeID = staffTypeID;
        this.departmentID = departmentID;
        this.accountID = accountID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getStaffTypeID() {
        return StaffTypeID;
    }

    public void setStaffTypeID(long staffTypeID) {
        StaffTypeID = staffTypeID;
    }

    public long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && StaffTypeID == staff.StaffTypeID && departmentID == staff.departmentID && accountID == staff.accountID && firstname.equals(staff.firstname) && Lastname.equals(staff.Lastname) && Objects.equals(picture, staff.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, Lastname, picture, StaffTypeID, departmentID, accountID);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", StaffTypeID=" + StaffTypeID +
                ", departmentID=" + departmentID +
                ", accountID=" + accountID +
                '}';
    }
}
