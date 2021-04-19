package com.hospital.service;

import com.hospital.service.impl.*;

/**
 * The class that serves as a provider for the service layer
 */
public final class ServiceProvider {

    /**
     * Instance of {@link ServiceProvider}
     */
    private static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    /**
     * Instance of {@link AccountService}
     */
    private final AccountService accountService = new AccountServiceImpl();

    /**
     * Instance of {@link StaffService}
     */
    private final StaffService staffService = new StaffServiceImpl();

    /**
     * Instance of {@link PatientService}
     */
    private final PatientService patientService = new PatientServiceImpl();

    /**
     * Instance of {@link AppointmentService}
     */
    private final AppointmentService appointmentService = new AppointmentServiceImpl();

    /**
     * Instance of {@link EpicrisisService}
     */
    private final EpicrisisService epicrisisService = new EpicrisisServiceImpl();

    /**
     * Instance of {@link MedicalHistoryService}
     */
    private final MedicalHistoryService medicalHistoryService = new MedicalHistoryServiceImpl();

    /**
     * Get instance of this class
     *
     * @return {@link ServiceProvider} instance
     */
    public static ServiceProvider getInstance(){return instance;}

    /**
     * Get instance of {@link AccountService}
     *
     * @return {@link AccountService} instance
     */
    public AccountService getAccountService(){
        return accountService;
    }

    /**
     * Get instance of {@link StaffService}
     *
     * @return {@link StaffService} instance
     */
    public StaffService getStaffService() {return staffService;}

    /**
     * Get instance of {@link PatientService}
     *
     * @return {@link PatientService} instance
     */
    public PatientService getPatientService() {return patientService;}

    /**
     * Get instance of {@link AppointmentService}
     *
     * @return {@link AppointmentService} instance
     */
    public AppointmentService getAppointmentService() {return appointmentService;}

    /**
     * Get instance of {@link EpicrisisService}
     *
     * @return {@link EpicrisisService} instance
     */
    public EpicrisisService getEpicrisisService() {return epicrisisService;}

    /**
     * Get instance of {@link MedicalHistoryService}
     *
     * @return {@link MedicalHistoryService} instance
     */
    public MedicalHistoryService getMedicalHistoryService() {return medicalHistoryService;}
}
