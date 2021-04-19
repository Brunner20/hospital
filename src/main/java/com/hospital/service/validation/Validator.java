package com.hospital.service.validation;

import com.hospital.bean.UserInfo;


/**
 * The class for data validation
 */
public class Validator {

    /**
     * regular expression for validation login
     */
    private static final String LOGIN_REGEX = "[A-Za-zА-Яа-я0-9_]+";
    /**
     * regular expression for validation password
     */
    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";
    /**
     * regular expression for validation id
     */
    private static final String ID_REGEX = "[0-9]+";



    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isLoginValid(String login){
        return login != null && login.matches(LOGIN_REGEX);
    }

    public static boolean isRegistrationInfoValid(UserInfo reg){

        return isLoginValid(reg.getLogin()) && isPasswordValid(reg.getPassword());
    }

    public static boolean isIdValid(long id){
        return  String.valueOf(id).matches(ID_REGEX);
    }

    public static boolean isTitleValid(String title) {return title!=null;}


}
