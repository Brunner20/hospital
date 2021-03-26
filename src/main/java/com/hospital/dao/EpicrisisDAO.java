package com.hospital.dao;

import com.hospital.entity.Epicrisis;

public interface EpicrisisDAO {

    void addEpicrisis(Epicrisis epicrisis) throws DAOException;

    Epicrisis getEpicrisisByPatientId(long patientId)throws DAOException;

    void update(Epicrisis epicrisis) throws DAOException;
}
