package com.hospital.dao;

import com.hospital.entity.Account;
import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Visitor;

public interface AccountDAO {
    Visitor authorization (String login, String password) throws DAOException;
    boolean	registration(RegistrationInfo regInfo) throws DAOException;
}
