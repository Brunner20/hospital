package com.hospital.service;

import com.hospital.entity.Patient;

import java.util.List;

public interface StaffService {

    List<Patient> getAllPatients(long id) throws ServiceException;
}
