package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    public static final int MIN_PASSWORD_LENGTH = 10;

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
        if (!checkPasswordPolicy(password)){
            flash.error("Password should be at least 10 characters long and bla bla bla");
            register();
            return;
        }

        User u = new User(username, HashUtils.getMd5(password), type, -1);
        u.save();
        registerComplete();
    }

    public static void registerComplete(){
        render();
    }

    public static boolean checkPasswordPolicy(String password){
        if (password.length() < MIN_PASSWORD_LENGTH){
            return false;
        }
        return true;
    }
}
