package com.hospital.service.impl;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Visitor;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;

public class AccountServiceImpl implements AccountService {

    private static final String WRONG_LOGIN_OR_PASSWORD = "login and password are required";
    private static final String WRONG_REG_INFO = "name and surname are required";

    @Override
    public Visitor authorization(String login, String password) throws ServiceException {

        if(!Validator.isPasswordValid(password)&&!Validator.isLoginValid(login))
        {
            throw new ServiceException(WRONG_LOGIN_OR_PASSWORD);
        }

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();

        Visitor visitor ;
        try {
          visitor = userDAO.authorization(login,password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return visitor;
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) throws ServiceException {

        if(!Validator.isRegistrationInfoValid(regInfo))
        {
          return true;
        }

        DAOProvider provider = DAOProvider.getInstance();
        AccountDAO userDAO = provider.getAccountDAO();

        boolean isRegistered;
        try {
            isRegistered = userDAO.registration(regInfo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isRegistered;
    }
}
