package com.hospital;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.entity.Patient;
import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Staff;
import com.hospital.entity.Visitor;

public class Main {

    public static void main(String[] args) throws DAOException {

       AccountDAO accountDAO = DAOProvider.getInstance().getAccountDAO();
       boolean registered = accountDAO.registration(new RegistrationInfo("qwer","surinov","ton","ton"));

       if(registered)
       {
           System.out.println("yes");
       }else System.out.println("no");

    }
}
