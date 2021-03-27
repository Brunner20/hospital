package com.hospital.entity;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Epicrisis {

    private long id;
    private String definitiveDiagnosis;
    private String preliminaryDiagnosis;
    private Date receiptDate;
    private Date dischargeDate;
    private long patientId;
    private long medicalHistoryId;
    private List<Long> appointmentsId;


    public Epicrisis(long id, String definitiveDiagnosis, String preliminaryDiagnosis, Date receiptDate,
                     Date dischargeDate, long patientId, long medicalHistoryId, List<Long> appointmentsId) {
        this.id = id;
        this.definitiveDiagnosis = definitiveDiagnosis;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.receiptDate = receiptDate;
        this.dischargeDate = dischargeDate;
        this.patientId = patientId;
        this.medicalHistoryId = medicalHistoryId;
        this.appointmentsId = appointmentsId;
    }

    public Epicrisis() {
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

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public List<Long> getAppointmentsId() {
        return appointmentsId;
    }

    public void setAppointmentsId(List<Long> appointmentsId) {
        this.appointmentsId = appointmentsId;
    }

    public void addAppointmentsId(Long appointmentsId) {
        this.appointmentsId.add(appointmentsId);
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epicrisis epicrisis = (Epicrisis) o;
        return id == epicrisis.id && patientId == epicrisis.patientId && medicalHistoryId == epicrisis.medicalHistoryId && Objects.equals(definitiveDiagnosis, epicrisis.definitiveDiagnosis) && Objects.equals(preliminaryDiagnosis, epicrisis.preliminaryDiagnosis) && Objects.equals(receiptDate, epicrisis.receiptDate) && Objects.equals(dischargeDate, epicrisis.dischargeDate) && Objects.equals(appointmentsId, epicrisis.appointmentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, preliminaryDiagnosis, receiptDate, dischargeDate, patientId, medicalHistoryId, appointmentsId);
    }

    @Override
    public String toString() {
        return "Epicrisis{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", receiptDate=" + receiptDate +
                ", dischargeDate=" + dischargeDate +
                ", patientId=" + patientId +
                ", medicalHistoryId=" + medicalHistoryId +
                ", appointmentsId=" + appointmentsId +
                '}';
    }
}
