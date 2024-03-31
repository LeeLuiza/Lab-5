package com.example.model;

public class AccountUser {
    private String login;
    private String email;
    private String password;

    public AccountUser(String login, String email, String password){
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin(){
        return login;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
