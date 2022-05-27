package com.revature.dao;

import com.revature.models.User;

import java.util.List;

public interface IUser {
    public void createUser(User u);

    public User employeeViewAccountInfoByID(int id) ;

    public User employeeViewAccountInfoByEmail(String email);

    public User employeeUpdateAccountInfo(User u);

    public List<User> managerViewAllEmployees();

    public void deleteUser(User u);

}