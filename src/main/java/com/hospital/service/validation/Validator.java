package com.hospital.service.validation;

import com.hospital.bean.UserInfo;


/**
 * The class for data validation
 */
public class Validator {
    /**
     * regular expression for validation name
     */
    private static final String NAME_REGEX = "[A-ZА-Я][a-zа-я]+";
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
    /**
     * regular expression for validation age
     */
    private static final String AGE_REGEX = "[1-9][0-9]*";

    public static boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public static boolean isSurnameValid(String surname) {
        return surname != null && surname.matches(NAME_REGEX);
    }

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

    public static boolean isAgeValid(String age) {
        boolean isValid = age.matches(AGE_REGEX);
       try {
           Integer.parseInt(age);
       }catch (NumberFormatException e ){
           isValid = false;
       }
       return isValid;
    }
}
