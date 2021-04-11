package com.hospital.service.validation;

import com.hospital.bean.UserInfo;

public class Validator {
    private static final String NAME_REGEX = "[A-ZА-Я][a-zа-я]+";
    private static final String LOGIN_REGEX = "[A-Za-zА-Яа-я0-9_]+";
    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";
    private static final String ID_REGEX = "[0-9]+";
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

    public static boolean isIdValid(String id){
        return  id.matches(ID_REGEX);
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
