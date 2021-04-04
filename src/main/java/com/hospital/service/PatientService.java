package com.hospital.service;

import com.hospital.entity.Patient;
import com.hospital.service.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface PatientService {



    List<Patient> getFreePatients() throws ServiceException;

    List<Patient> getAllPatientsByStaff(long id) throws ServiceException;

    List<Patient> getAll() throws ServiceException;

    void updateDoctor(List<String> selectedPatientsIds, Long doctorId) throws ServiceException;

    Patient getPatientById(Long id) throws ServiceException;

    void update(Patient patient) throws ServiceException;

    void savePictureToPatient(Patient patient, Part part) throws ServiceException;
}
