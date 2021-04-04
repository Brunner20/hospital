package com.hospital.service;

import com.hospital.entity.Epicrisis;
import com.hospital.entity.dto.EpicrisisDTO;
import com.hospital.service.exception.ServiceException;

import java.util.List;

public interface EpicrisisService {


    void addEpicrisis(Epicrisis epicrisis) throws ServiceException;

    List<EpicrisisDTO> getEpicrisisByPatientId(long patientID) throws ServiceException;

    void update(Epicrisis epicrisis) throws ServiceException;

    Epicrisis getLastEpicrisisByPatientId(long patientID) throws ServiceException;

}
