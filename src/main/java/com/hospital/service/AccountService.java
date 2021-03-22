package com.hospital.service;

import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Visitor;

public interface AccountService {

    Visitor authorization(String login, String password) throws ServiceException;
    boolean registration(RegistrationInfo regInfo) throws ServiceException;
}
