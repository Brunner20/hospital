package com.hospital.bean.dto;

import com.hospital.bean.AppointmentStatus;
import com.hospital.bean.AppointmentType;

import java.sql.Date;
import java.util.Objects;

/**
 * data transfer object for {@link com.hospital.bean.Appointment} class
 *
 * @author Anton Brunner
 */
public class AppointmentDTO {

    private long id;
    private Date dateOfAppointment;
    private Date dateOfCompletion;
    private String patientFirstname;
    private String patientLastname;
    private String appointingDoctorFirstname;
    private String appointingDoctorLastname;
    private String executeStaffFirstname;
    private String executeStaffLastname;
    private String info;
    private AppointmentType type;
    private AppointmentStatus status;

    public AppointmentDTO(long id, Date dateOfAppointment, Date dateOfCompletion, String patientFirstname, String patientLastname, String appointingDoctorFirstname, String appointingDoctorLastname, String executeStaffFirstname, String executeStaffLastname, String info, AppointmentType type, AppointmentStatus status) {
        this.id = id;
        this.dateOfAppointment = dateOfAppointment;
        this.dateOfCompletion = dateOfCompletion;
        this.patientFirstname = patientFirstname;
        this.patientLastname = patientLastname;
        this.appointingDoctorFirstname = appointingDoctorFirstname;
        this.appointingDoctorLastname = appointingDoctorLastname;
        this.executeStaffFirstname = executeStaffFirstname;
        this.executeStaffLastname = executeStaffLastname;
        this.info = info;
        this.type = type;
        this.status = status;
    }

    public AppointmentDTO() {
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getPatientFirstname() {
        return patientFirstname;
    }

    public void setPatientFirstname(String patientFirstname) {
        this.patientFirstname = patientFirstname;
    }

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public String getAppointingDoctorFirstname() {
        return appointingDoctorFirstname;
    }

    public void setAppointingDoctorFirstname(String appointingDoctorFirstname) {
        this.appointingDoctorFirstname = appointingDoctorFirstname;
    }

    public String getAppointingDoctorLastname() {
        return appointingDoctorLastname;
    }

    public void setAppointingDoctorLastname(String appointingDoctorLastname) {
        this.appointingDoctorLastname = appointingDoctorLastname;
    }

    public String getExecuteStaffFirstname() {
        return executeStaffFirstname;
    }

    public void setExecuteStaffFirstname(String executeStaffFirstname) {
        this.executeStaffFirstname = executeStaffFirstname;
    }

    public String getExecuteStaffLastname() {
        return executeStaffLastname;
    }

    public void setExecuteStaffLastname(String executeStaffLastname) {
        this.executeStaffLastname = executeStaffLastname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDTO that = (AppointmentDTO) o;
        return id == that.id && Objects.equals(dateOfAppointment, that.dateOfAppointment) && Objects.equals(dateOfCompletion, that.dateOfCompletion) && Objects.equals(patientFirstname, that.patientFirstname) && Objects.equals(patientLastname, that.patientLastname) && Objects.equals(appointingDoctorFirstname, that.appointingDoctorFirstname) && Objects.equals(appointingDoctorLastname, that.appointingDoctorLastname) && Objects.equals(executeStaffFirstname, that.executeStaffFirstname) && Objects.equals(executeStaffLastname, that.executeStaffLastname) && Objects.equals(info, that.info) && type == that.type && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfAppointment, dateOfCompletion, patientFirstname, patientLastname, appointingDoctorFirstname, appointingDoctorLastname, executeStaffFirstname, executeStaffLastname, info, type, status);
    }

    @Override
    public String
    toString() {
        return "InformationAboutAppointment{" +
                "id=" + id +
                ", dateOfAppointment=" + dateOfAppointment +
                ", dateOfCompletion=" + dateOfCompletion +
                ", patientFirstname='" + patientFirstname + '\'' +
                ", patientLastname='" + patientLastname + '\'' +
                ", appointingDoctorFirstname='" + appointingDoctorFirstname + '\'' +
                ", appointingDoctorLastname='" + appointingDoctorLastname + '\'' +
                ", executeStaffFirstname='" + executeStaffFirstname + '\'' +
                ", executeStaffLastname='" + executeStaffLastname + '\'' +
                ", info='" + info + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
