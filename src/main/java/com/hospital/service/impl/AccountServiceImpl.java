package com.hospital.service.impl;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.dao.exception.DataNotFoundException;
import com.hospital.entity.UserInfo;
import com.hospital.entity.Visitor;
import com.hospital.service.AccountService;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.DataNotFoundServiceException;
import com.hospital.service.exception.LoginIsBusyServiceException;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.validation.Validator;

public class AccountServiceImpl implements AccountService {

    private static final String WRONG_LOGIN_OR_PASSWORD = "login and password are invalid";

    @Override
    public Visitor authorization(String login, String password) throws ServiceException {

        if(!Validator.isPasswordValid(password)&&!Validator.isLoginValid(login))
        {
            throw new DataFormatServiceException(WRONG_LOGIN_OR_PASSWORD);
        }

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();

        Visitor visitor = null;
        try {
          visitor = userDAO.authorization(login,password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return visitor;
    }

    @Override
    public void registration(UserInfo regInfo) throws ServiceException {

        if(!Validator.isRegistrationInfoValid(regInfo))
        {
            throw new DataFormatServiceException("user data invalid");
        }
        if(!isFreeLogin(regInfo.getLogin())){
                throw new LoginIsBusyServiceException("login is busy");
        }

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();
        try {
            userDAO.registration(regInfo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }

    private boolean isFreeLogin(String login) throws ServiceException {

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();

        Long id;
        try {
           id = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return id == null;

    }

    @Override
    public void updatePassword(long accountId, String oldPass, String newPass) throws ServiceException {
        if(!Validator.isPasswordValid(newPass)){
            throw new DataFormatServiceException("invalid new password");
        }

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();
        try {
           userDAO.updatePassword(accountId,oldPass,newPass);
        }catch (DataNotFoundException e){
            throw new DataNotFoundServiceException(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
}
