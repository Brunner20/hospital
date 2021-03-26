package com.hospital.entity;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Epicrisis {

    private long id;
    private String definitiveDiagnosis;
    private String preliminaryDiagnosis;
    private Date dischargeDate;
    private long patientId;
    private List<Long> appointmentsId;



    public Epicrisis(long id, String definitiveDiagnosis, String preliminaryDiagnosis, Date dischargeDate, long patientId, List<Long> appointmentsId) {
        this.id = id;
        this.definitiveDiagnosis = definitiveDiagnosis;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.dischargeDate = dischargeDate;
        this.patientId = patientId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epicrisis epicrisis = (Epicrisis) o;
        return id == epicrisis.id && patientId == epicrisis.patientId && Objects.equals(definitiveDiagnosis, epicrisis.definitiveDiagnosis) && Objects.equals(preliminaryDiagnosis, epicrisis.preliminaryDiagnosis) && Objects.equals(dischargeDate, epicrisis.dischargeDate) && Objects.equals(appointmentsId, epicrisis.appointmentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, preliminaryDiagnosis, dischargeDate, patientId, appointmentsId);
    }

    @Override
    public String toString() {
        return "Epicrisis{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", dischargeDate=" + dischargeDate +
                ", patientId=" + patientId +
                ", appointmentsId=" + appointmentsId +
                '}';
    }
}
