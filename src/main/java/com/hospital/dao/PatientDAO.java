package com.hospital.dao;

import com.hospital.entity.Patient;

import java.util.List;

public interface PatientDAO {

    void update(Patient patient) throws DAOException;

    void updateAge(long id,int age) throws DAOException;

    List<Patient> getFreePatients() throws DAOException;

    void updateDoctor(List<String> selectedPatientsIds, Long doctorId) throws DAOException;

    Patient getPatientById(Long id)throws DAOException;
}
