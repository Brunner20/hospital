package com.hospital.entity;

import java.time.LocalDate;
import java.util.Objects;

public class MedicalHistory {

    private long id;
    private String preliminaryDiagnosis;
    private LocalDate dateOfReceipt;
    private LocalDate dateOfDischarge;
    private long patientId;
    private long epicrisisId;

    public MedicalHistory(long id, String preliminaryDiagnosis, LocalDate dateOfReceipt,
                          LocalDate dateOfDischarge, long patientId, long epicrisisId) {
        this.id = id;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfDischarge = dateOfDischarge;
        this.patientId = patientId;
        this.epicrisisId = epicrisisId;
    }

    public MedicalHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getEpicrisisId() {
        return epicrisisId;
    }

    public void setEpicrisisId(long epicrisisId) {
        this.epicrisisId = epicrisisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalHistory that = (MedicalHistory) o;
        return id == that.id && patientId == that.patientId && epicrisisId == that.epicrisisId && Objects.equals(preliminaryDiagnosis, that.preliminaryDiagnosis) && Objects.equals(dateOfReceipt, that.dateOfReceipt) && Objects.equals(dateOfDischarge, that.dateOfDischarge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preliminaryDiagnosis, dateOfReceipt, dateOfDischarge, patientId, epicrisisId);
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", dateOfReceipt=" + dateOfReceipt +
                ", dateOfDischarge=" + dateOfDischarge +
                ", patientId=" + patientId +
                ", epicrisisId=" + epicrisisId +
                '}';
    }
}
