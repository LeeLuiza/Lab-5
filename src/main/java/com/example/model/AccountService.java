package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static Map<String, AccountUser> loginToPtofile = new HashMap<>();

    public static void addNewUser(AccountUser user){
        loginToPtofile.put(user.getLogin(), user);
    }

    public static AccountUser getUserByLogin(String login){
        return loginToPtofile.get(login);
    }
}
