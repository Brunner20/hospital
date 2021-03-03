package com.hospital;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.entity.Visitor;

public class Main {

    public static void main(String[] args) throws DAOException {

       AccountDAO accountDAO = DAOProvider.getInstance().getAccountDAO();
       Visitor visitor = accountDAO.authorization("ton","ton");

        System.out.println("dfg");

    }
}
