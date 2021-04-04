package com.hospital.dao;

import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Epicrisis;

import java.util.List;

public interface EpicrisisDAO {

    void addEpicrisis(Epicrisis epicrisis) throws DAOException;

    List<Epicrisis> getEpicrisisByPatientId(long patientId)throws DAOException;

    void update(Epicrisis epicrisis) throws DAOException;
}
