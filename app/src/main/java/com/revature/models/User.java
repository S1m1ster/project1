package com.revature.models;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private int user_role;

    public User(){

    }

    public User(int user_id) {
        this.user_id = user_id;
    }

    public User(String username, String password, String first_name, String last_name, String email) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public User(int user_id, String username, String password, String first_name, String last_name, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public User(int user_id, String username, String password, String first_name, String last_name, String email, int user_role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.user_role = user_role;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "User{" +"\n" +
                "user_id=" + user_id + "\n" +
                ", username='" + username + "\n" +
                ", password='" + password + "\n" +
                ", first_name='" + first_name + "\n" +
                ", last_name='" + last_name + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", user_role=" + user_role +
                '}';
    }
}
