package com.hospital.service;

import com.hospital.entity.UserInfo;
import com.hospital.entity.Visitor;
import com.hospital.service.exception.ServiceException;

public interface AccountService {

    Visitor authorization(String login, String password) throws ServiceException;
    void registration(UserInfo regInfo) throws ServiceException;
    void updatePassword(long accountId, String oldPass, String newPass) throws ServiceException;
}
