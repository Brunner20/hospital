package com.hospital.service;

import com.hospital.service.impl.AccountServiceImpl;
import com.hospital.service.impl.DocumentationServiceImpl;
import com.hospital.service.impl.PatientServiceImpl;
import com.hospital.service.impl.StaffServiceImpl;

public final class ServiceProvider {

    private static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final AccountService accountService = new AccountServiceImpl();

    private final StaffService staffService = new StaffServiceImpl();

    private final PatientService patientService = new PatientServiceImpl();

    private final DocumentationService documentationService = new DocumentationServiceImpl();

    public static ServiceProvider getInstance(){return instance;}

    public AccountService getAccountService(){
        return accountService;
    }

    public StaffService getStaffService() {return staffService;}

    public PatientService getPatientService() {return patientService;}

    public DocumentationService getDocumentationService() {return documentationService;}
}
