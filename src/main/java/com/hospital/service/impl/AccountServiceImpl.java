package com.hospital.service.impl;

import com.hospital.bean.Account;
import com.hospital.bean.UserInfo;
import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.dao.exception.DataNotFoundException;
import com.hospital.service.AccountService;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.DataNotFoundServiceException;
import com.hospital.service.exception.LoginIsBusyServiceException;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class containing business logic to work with account
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    private static final String WRONG_LOGIN_OR_PASSWORD = "login and password are invalid";
    private static final String LOGIN_BUSY = "login is busy";
    private static final String INVALID = "user data invalid";

    /**
     * Instance of {@link DAOProvider}
     */
    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public Account authorization(String login, String password) throws ServiceException {
        login = login.trim();
        password = password.trim();
        if(!Validator.isPasswordValid(password)&&!Validator.isLoginValid(login))
        {
            logger.log(Level.WARN,WRONG_LOGIN_OR_PASSWORD);
            throw new DataFormatServiceException(WRONG_LOGIN_OR_PASSWORD);
        }
        AccountDAO userDAO = provider.getAccountDAO();
        Account account;
        try {
          account = userDAO.authorization(login,password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return account;
    }

    @Override
    public void registration(UserInfo regInfo) throws ServiceException {
        if(!Validator.isRegistrationInfoValid(regInfo))
        {
            logger.log(Level.WARN,INVALID);
            throw new DataFormatServiceException(INVALID);
        }
        if(!isFreeLogin(regInfo.getLogin())){
            logger.log(Level.WARN,LOGIN_BUSY);
            throw new LoginIsBusyServiceException(LOGIN_BUSY);
        }
        AccountDAO userDAO = provider.getAccountDAO();
        try {
            userDAO.registration(regInfo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isFreeLogin(String login) throws ServiceException {
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
            logger.log(Level.WARN, INVALID);
            throw new DataFormatServiceException(INVALID);
        }
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
