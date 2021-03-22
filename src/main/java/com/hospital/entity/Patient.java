package com.hospital.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Patient implements Visitor{

    private long id;
    private String firstname;
    private String Lastname;
    private int age;
    private LocalDate receiptDate;
    private Department department;
    private long attendingDoctorID;
    private long statusID;
    private long accountID;


    public Patient() {
    }

    public Patient(long id, String firstname, String lastname, int age, LocalDate receiptDate, Department department,
                   long attendingDoctorID, long statusID, long accountID) {
        this.id = id;
        this.firstname = firstname;
        Lastname = lastname;
        this.age = age;
        this.receiptDate = receiptDate;
        this.department = department;
        this.attendingDoctorID = attendingDoctorID;
        this.statusID = statusID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public long getAttendingDoctorID() {
        return attendingDoctorID;
    }

    public void setAttendingDoctorID(long attendingDoctorID) {
        this.attendingDoctorID = attendingDoctorID;
    }

    public long getStatusID() {
        return statusID;
    }

    public void setStatusID(long statusID) {
        this.statusID = statusID;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(int departmentId) {
        this.department = Department.getDepartmentById(departmentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && attendingDoctorID == patient.attendingDoctorID && statusID == patient.statusID && accountID == patient.accountID && Objects.equals(firstname, patient.firstname) && Objects.equals(Lastname, patient.Lastname) && Objects.equals(receiptDate, patient.receiptDate) && department == patient.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, Lastname, age, receiptDate, department, attendingDoctorID, statusID, accountID);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", age=" + age +
                ", receiptDate=" + receiptDate +
                ", department=" + department +
                ", attendingDoctorID=" + attendingDoctorID +
                ", statusID=" + statusID +
                ", accountID=" + accountID +
                '}';
    }
}
