package com.hospital.entity;

import java.sql.Date;
import java.util.Objects;

public class Patient implements Visitor{

    private long id;
    private String firstname;
    private String lastname;
    private int age;
    private Date receiptDate;
    private Department department;
    private Long attendingDoctorID;
    private Long statusID;
    private long accountID;
    private String patientPic;

    public Patient() {
    }

    public Patient(long id, String firstname, String lastname, int age, Date receiptDate,
                   Department department, long attendingDoctorID, long statusID, long accountID, String patientPic) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.receiptDate = receiptDate;
        this.department = department;
        this.attendingDoctorID = attendingDoctorID;
        this.statusID = statusID;
        this.accountID = accountID;
        this.patientPic = patientPic;
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
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getAttendingDoctorID() {
        return attendingDoctorID;
    }

    public void setAttendingDoctorID(Long attendingDoctorID) {
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

    public String getPatientPic() {
        return patientPic;
    }

    public void setPatientPic(String patientPic) {
        this.patientPic = patientPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && attendingDoctorID == patient.attendingDoctorID && statusID == patient.statusID && accountID == patient.accountID && Objects.equals(firstname, patient.firstname) && Objects.equals(lastname, patient.lastname) && Objects.equals(receiptDate, patient.receiptDate) && department == patient.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, age, receiptDate, department, attendingDoctorID, statusID, accountID);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", age=" + age +
                ", receiptDate=" + receiptDate +
                ", department=" + department +
                ", attendingDoctorID=" + attendingDoctorID +
                ", statusID=" + statusID +
                ", accountID=" + accountID +
                '}';
    }
}
