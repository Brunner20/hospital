package com.hospital.dao;

import com.hospital.dao.impl.*;

public final class DAOProvider {

    private static DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private AccountDAO accountDAO = new AccountDAOImpl();

    private PatientDAO patientDAO = new PatientDAOImpl();

    private StaffDAO staffDAO = new StaffDAOImpl();

    private AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    private EpicrisisDAO epicrisisDAO = new EpicrisisDAOImpl();

    public static DAOProvider getInstance(){
        return instance;
    }

    public AccountDAO getAccountDAO(){
        return accountDAO;
    }

    public PatientDAO getPatientDAO() { return  patientDAO; }

    public StaffDAO getStaffDAO(){ return  staffDAO ;}

    public AppointmentDAO getAppointmentDAO() {return appointmentDAO;}

    public EpicrisisDAO getEpicrisisDAO() {return epicrisisDAO;}
}
