package com.hospital.service;

import com.hospital.entity.Epicrisis;

public interface EpicrisisService {


    void addEpicrisis(Epicrisis epicrisis) throws ServiceException;

    Epicrisis getEpicrisisByPatientId(long patientID) throws ServiceException;

    void update(Epicrisis epicrisis) throws ServiceException;
}
