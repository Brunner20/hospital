package com.hospital.dao;

import com.hospital.dao.impl.AccountDAOImpl;
import com.hospital.dao.impl.PatientDAOImpl;
import com.hospital.dao.impl.StaffDAOImpl;

public final class DAOProvider {

    private static DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private AccountDAO accountDAO = new AccountDAOImpl();

    private PatientDAO patientDAO = new PatientDAOImpl();

    private StaffDAO staffDAO = new StaffDAOImpl();

    public static DAOProvider getInstance(){
        return instance;
    }

    public AccountDAO getAccountDAO(){
        return accountDAO;
    }

    public PatientDAO getPatientDAO() { return  patientDAO; }

    public StaffDAO getStaffDAO(){ return  staffDAO ;}
}
