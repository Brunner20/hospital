package com.hospital.bean.dto;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * data transfer object for {@link com.hospital.bean.Epicrisis} class
 *
 * @author Anton Brunner
 */
public class EpicrisisDTO {

    private long id;
    private String definitiveDiagnosis;
    private String preliminaryDiagnosis;
    private Date receiptDate;
    private Date dischargeDate;
    private String patientFirstname;
    private String patientLastname;
    private List<AppointmentDTO> appointmentList;

    public EpicrisisDTO(long id, String definitiveDiagnosis, String preliminaryDiagnosis, Date receiptDate,
                        Date dischargeDate, String patientFirstname, String patientLastname, List<AppointmentDTO> appointmentList) {
        this.id = id;
        this.definitiveDiagnosis = definitiveDiagnosis;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.receiptDate = receiptDate;
        this.dischargeDate = dischargeDate;
        this.patientFirstname = patientFirstname;
        this.patientLastname = patientLastname;
        this.appointmentList = appointmentList;
    }

    public EpicrisisDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDefinitiveDiagnosis() {
        return definitiveDiagnosis;
    }

    public void setDefinitiveDiagnosis(String definitiveDiagnosis) {
        this.definitiveDiagnosis = definitiveDiagnosis;
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
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

    public List<AppointmentDTO> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<AppointmentDTO> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpicrisisDTO that = (EpicrisisDTO) o;
        return id == that.id && Objects.equals(definitiveDiagnosis, that.definitiveDiagnosis) && Objects.equals(preliminaryDiagnosis, that.preliminaryDiagnosis) && Objects.equals(receiptDate, that.receiptDate) && Objects.equals(dischargeDate, that.dischargeDate) && Objects.equals(patientFirstname, that.patientFirstname) && Objects.equals(patientLastname, that.patientLastname) && Objects.equals(appointmentList, that.appointmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, preliminaryDiagnosis, receiptDate, dischargeDate, patientFirstname, patientLastname, appointmentList);
    }

    @Override
    public String toString() {
        return "EpicrisisDTO{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", receiptDate=" + receiptDate +
                ", dischargeDate=" + dischargeDate +
                ", patientFirstname='" + patientFirstname + '\'' +
                ", patientLastname='" + patientLastname + '\'' +
                ", appointmentList=" + appointmentList +
                '}';
    }
}
