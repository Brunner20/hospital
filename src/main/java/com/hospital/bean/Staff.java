package com.hospital.bean;

import java.util.Objects;

/**
 * The entity Staff
 *
 * @author Anton Brunner
 */
public class Staff implements Visitor {

    private long id;
    private String firstname;
    private String Lastname;
    private String picture;
    private long StaffTypeID;
    private Department department;
    private long accountID;

    public Staff() {
    }

    public Staff(long id, String firstname, String lastname, String picture, long staffTypeID, Department department, long accountID) {
        this.id = id;
        this.firstname = firstname;
        Lastname = lastname;
        this.picture = picture;
        StaffTypeID = staffTypeID;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(int departmentId) {
        this.department = Department.getDepartmentById(departmentId);
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
        return id == staff.id && StaffTypeID == staff.StaffTypeID && accountID == staff.accountID && Objects.equals(firstname, staff.firstname) && Objects.equals(Lastname, staff.Lastname) && Objects.equals(picture, staff.picture) && department == staff.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, Lastname, picture, StaffTypeID, department, accountID);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", picture='" + picture + '\'' +
                ", StaffTypeID=" + StaffTypeID +
                ", department=" + department +
                ", accountID=" + accountID +
                '}';
    }
}
