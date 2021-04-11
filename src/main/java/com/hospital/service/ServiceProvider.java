package com.hospital.service;

import com.hospital.service.impl.*;

/**
 * The class that serves as a provider for the service layer
 */
public final class ServiceProvider {

    private static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final AccountService accountService = new AccountServiceImpl();

    private final StaffService staffService = new StaffServiceImpl();

    private final PatientService patientService = new PatientServiceImpl();

    private final AppointmentService appointmentService = new AppointmentServiceImpl();

    private final EpicrisisService epicrisisService = new EpicrisisServiceImpl();

    private final MedicalHistoryService medicalHistoryService = new MedicalHistoryServiceImpl();

    public static ServiceProvider getInstance(){return instance;}

    public AccountService getAccountService(){
        return accountService;
    }

    public StaffService getStaffService() {return staffService;}

    public PatientService getPatientService() {return patientService;}

    public AppointmentService getAppointmentService() {return appointmentService;}

    public EpicrisisService getEpicrisisService() {return epicrisisService;}

    public MedicalHistoryService getMedicalHistoryService() {return medicalHistoryService;}
}
