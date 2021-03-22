package com.hospital.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Appointment {
    private long id;
    private LocalDate dateOfAppointment;
    private LocalDate dateOfCompletion;
    private long patientId;
    private long appointingDoctorId;
    private long executeStaffId;
    private long infoId;
    private AppointmentStatus status;
    private String title;


    public Appointment(long id, LocalDate dateOfAppointment, LocalDate dateOfCompletion, long patientId,
                       long appointingDoctorId, long executeStaffId, long infoId, AppointmentStatus status, String title) {
        this.id = id;
        this.dateOfAppointment = dateOfAppointment;
        this.dateOfCompletion = dateOfCompletion;
        this.patientId = patientId;
        this.appointingDoctorId = appointingDoctorId;
        this.executeStaffId = executeStaffId;
        this.infoId = infoId;
        this.status = status;
        this.title = title;
    }

    public Appointment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getAppointingDoctorId() {
        return appointingDoctorId;
    }

    public void setAppointingDoctorId(long appointingDoctorId) {
        this.appointingDoctorId = appointingDoctorId;
    }

    public long getExecuteStaffId() {
        return executeStaffId;
    }

    public void setExecuteStaffId(long executeStaffId) {
        this.executeStaffId = executeStaffId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = AppointmentStatus.getStatusById(status);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && patientId == that.patientId && appointingDoctorId == that.appointingDoctorId && executeStaffId == that.executeStaffId && infoId == that.infoId && Objects.equals(dateOfAppointment, that.dateOfAppointment) && Objects.equals(dateOfCompletion, that.dateOfCompletion) && status == that.status && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfAppointment, dateOfCompletion, patientId, appointingDoctorId, executeStaffId, infoId, status, title);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateOfAppointment=" + dateOfAppointment +
                ", dateOfCompletion=" + dateOfCompletion +
                ", patientId=" + patientId +
                ", appointingDoctorId=" + appointingDoctorId +
                ", executeStaffId=" + executeStaffId +
                ", infoId=" + infoId +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }
}
