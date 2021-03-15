package com.hospital.service.validation;

import com.hospital.entity.RegistrationInfo;

public class Validator {
    private static final String NAME_REGEX = "[A-Z][a-z]+";
    private static final String SURNAME_REGEX = "[A-Z][a-z]+";
    private static final String LOGIN_REGEX = "[A-Za-z0-9_]+";
    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private static final String ID_REGEX = "[0-9]+";
    private static final String AGE_REGEX = "[1-9][0-9]*";

    public static boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public static boolean isSurnameValid(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isLoginValid(String login){
        return login != null && login.matches(LOGIN_REGEX);
    }

    public static boolean isRegistrationInfoValid(RegistrationInfo reg){
        return isLoginValid(reg.getLogin()) && isNameValid(reg.getFirstname())
                && isSurnameValid(reg.getLastname()) && isPasswordValid(reg.getPassword());
    }

    public static boolean isIdValid(long id){
        return  String.valueOf(id).matches(ID_REGEX);
    }

    public static boolean isAgeValid(String age) {
        boolean isValid = age.matches(AGE_REGEX);
       try {
           int ageInt = Integer.parseInt(age);
       }catch (NumberFormatException e ){
           isValid = false;
       }
       return isValid;
    }
}
