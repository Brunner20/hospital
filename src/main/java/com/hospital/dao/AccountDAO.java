package com.hospital.dao;

import com.hospital.bean.Account;
import com.hospital.bean.UserInfo;
import com.hospital.dao.exception.DAOException;

/**
 * The interface account dao.
 */
public interface AccountDAO {
    /**
     * Authorize with login and password
     * @param login account login
     * @param password account password
     * @return found account
     * @throws DAOException if an dao exception occurred while processing
     */
    Account authorization (String login, String password) throws DAOException;

    /**
     * Register a new account
     * @param regInfo information for registration
     * @throws DAOException if an dao exception occurred while processing
     */
    void registration(UserInfo regInfo) throws DAOException;

    /**
     * Update password
     * @param accountId account id for which we update the password
     * @param oldPass old password
     * @param newPass new password
     * @throws DAOException if an dao exception occurred while processing
     */
    void updatePassword(long accountId, String oldPass, String newPass) throws DAOException;

    /**
     * Find account by login
     * @param login the account id
     * @return found account id
     * @throws DAOException if an dao exception occurred while processing
     */
    Long findByLogin(String login) throws DAOException;
}
