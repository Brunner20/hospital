package com.hospital.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Epicrisis {

    private long id;
    private String definitiveDiagnosis;
    private LocalDate issueDate;
    private long patientId;
    private List<Long> appointmentsId;

    public Epicrisis(long id, String definitiveDiagnosis, LocalDate issueDate, long patientId, List<Long> appointmentsId) {
        this.id = id;
        this.definitiveDiagnosis = definitiveDiagnosis;
        this.issueDate = issueDate;
        this.patientId = patientId;
        this.appointmentsId = appointmentsId;
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epicrisis epicrisis = (Epicrisis) o;
        return id == epicrisis.id && patientId == epicrisis.patientId && Objects.equals(definitiveDiagnosis, epicrisis.definitiveDiagnosis) && Objects.equals(issueDate, epicrisis.issueDate) && Objects.equals(appointmentsId, epicrisis.appointmentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, issueDate, patientId, appointmentsId);
    }

    @Override
    public String toString() {
        return "Epicrisis{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", issueDate=" + issueDate +
                ", patientId=" + patientId +
                ", appointmentsId=" + appointmentsId +
                '}';
    }
}
