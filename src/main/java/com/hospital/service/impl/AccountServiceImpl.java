package com.hospital.service.impl;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Visitor;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;

public class AccountServiceImpl implements AccountService {

    private static final String WRONG_LOGIN_OR_PASSWORD = "login and password are required";
    private static final String WRONG_REG_INFO = "name and surname are required";

    @Override
    public Visitor authorization(String login, String password) throws ServiceException {

        if(login==null||login.isEmpty()||password==null||password.isEmpty())
        {throw new ServiceException(WRONG_LOGIN_OR_PASSWORD);}

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
    public boolean registration(RegistrationInfo regInfo) throws ServiceException {

        if(regInfo.getFirstname()==null||regInfo.getFirstname().isEmpty()||
                regInfo.getLastname()==null||regInfo.getLastname().isEmpty())
        {throw new ServiceException(WRONG_REG_INFO);}

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
