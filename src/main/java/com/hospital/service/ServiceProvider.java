package com.hospital.service;

import com.hospital.service.impl.AccountServiceImpl;

public final class ServiceProvider {

    private static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final AccountService accountService = new AccountServiceImpl();

    public static ServiceProvider getInstance(){return instance;}

    public AccountService getAccountService(){
        return accountService;
    }
}
