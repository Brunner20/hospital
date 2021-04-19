package com.hospital.dao;

import com.hospital.dao.impl.*;
import com.hospital.service.AccountService;


/**
 * The class that serves as a provider for the dao layer
 */
public final class DAOProvider {

    /**
     * Instance of {@link DAOProvider}
     */
    private static DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    /**
     * Instance of {@link AccountDAO}
     */
    private AccountDAO accountDAO = new AccountDAOImpl();

    /**
     * Instance of {@link PatientDAO}
     */
    private PatientDAO patientDAO = new PatientDAOImpl();

    /**
     * Instance of {@link StaffDAO}
     */
    private StaffDAO staffDAO = new StaffDAOImpl();

    /**
     * Instance of {@link AppointmentDAO}
     */
    private AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    /**
     * Instance of {@link EpicrisisDAO}
     */
    private EpicrisisDAO epicrisisDAO = new EpicrisisDAOImpl();

    /**
     * Instance of {@link MedicalHistoryDAO}
     */
    private MedicalHistoryDAO medicalHistoryDAO = new MedicalHistoryDAOImpl();

    /**
     * Get instance of this class
     *
     * @return {@link DAOProvider} instance
     */
    public static DAOProvider getInstance(){
        return instance;
    }

    /**
     * Get instance of {@link AccountService}
     *
     * @return {@link AccountService} instance
     */
    public AccountDAO getAccountDAO(){
        return accountDAO;
    }

    /**
     * Get instance of {@link PatientDAO}
     *
     * @return {@link PatientDAO} instance
     */
    public PatientDAO getPatientDAO() { return  patientDAO; }

    /**
     * Get instance of {@link StaffDAO}
     *
     * @return {@link StaffDAO} instance
     */
    public StaffDAO getStaffDAO(){ return  staffDAO ;}

    /**
     * Get instance of {@link AppointmentDAO}
     *
     * @return {@link AppointmentDAO} instance
     */
    public AppointmentDAO getAppointmentDAO() {return appointmentDAO;}

    /**
     * Get instance of {@link EpicrisisDAO}
     *
     * @return {@link EpicrisisDAO} instance
     */
    public EpicrisisDAO getEpicrisisDAO() {return epicrisisDAO;}

    /**
     * Get instance of {@link MedicalHistoryDAO}
     *
     * @return {@link MedicalHistoryDAO} instance
     */
    public MedicalHistoryDAO getMedicalHistoryDAO() {return medicalHistoryDAO;}
}
