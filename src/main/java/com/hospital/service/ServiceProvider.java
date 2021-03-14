package com.hospital.service;

import com.hospital.service.impl.AccountServiceImpl;
import com.hospital.service.impl.StaffServiceImpl;

public final class ServiceProvider {

    private static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final AccountService accountService = new AccountServiceImpl();

    private final StaffService staffService = new StaffServiceImpl();

    public static ServiceProvider getInstance(){return instance;}

    public AccountService getAccountService(){
        return accountService;
    }

    public StaffService getStaffService() {return staffService;}
}
