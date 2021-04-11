package com.hospital.service;

import com.hospital.bean.Account;
import com.hospital.bean.UserInfo;
import com.hospital.service.exception.ServiceException;

/**
 * The interface account service.
 */
public interface AccountService {
    /**
     * Authorize with login and password
     * @param login account login
     * @param password account password
     * @return found account
     * @throws ServiceException if a service exception occurred while processing
     */
    Account authorization(String login, String password) throws ServiceException;

    /**
     * Register a new account
     * @param regInfo information for registration
     * @throws ServiceException if a service exception occurred while processing
     */
    void registration(UserInfo regInfo) throws ServiceException;

    /**
     * Update password
     * @param accountId account id for which we update the password
     * @param oldPass old password
     * @param newPass new password
     * @throws ServiceException if a service exception occurred while processing
     */
    void updatePassword(long accountId, String oldPass, String newPass) throws ServiceException;
}
