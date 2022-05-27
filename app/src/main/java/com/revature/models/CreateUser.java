package com.revature.models;

public class CreateUser {
    public String username;
    public String password;
    public String first_name;
    public String last_name;
    public String email;

    @Override
    public String toString() {
        return "CreateUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
