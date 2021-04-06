package com.hospital.dao;

import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Account;
import com.hospital.entity.UserInfo;

public interface AccountDAO {
    Account authorization (String login, String password) throws DAOException;
    void 	registration(UserInfo regInfo) throws DAOException;
    void updatePassword(long accountId, String oldPass, String newPass) throws DAOException;
    Long findByLogin(String login) throws DAOException;
}
