package com.hospital.service;

import com.hospital.entity.Patient;

import java.util.List;

public interface PatientService {

    void updateAge(long id, String age) throws ServiceException;

    List<Patient> getFreePatients() throws ServiceException;

    List<Patient> getAllPatientsByStaff(long id) throws ServiceException;

    List<Patient> getAll() throws ServiceException;

    void updateDoctor(List<String> selectedPatientsIds, Long doctorId) throws ServiceException;

    Patient getPatientById(Long id) throws ServiceException;

    void update(Patient patient) throws ServiceException;
}
