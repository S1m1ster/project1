package com.revature.services;

import com.revature.dao.IReimbursement;
import com.revature.dao.IUser;
import com.revature.models.User;

import java.util.List;

public class UserService {
    private IUser ud;
    private IReimbursement rd;


    public UserService(IUser ud, IReimbursement rd){
        this.ud = ud;
        this.rd = rd;

    }
    public User loginUser(String email, String password){
        User u = ud.employeeViewAccountInfoByEmail(email);

        if(u != null){
            if(password.equals(u.getPassword())){
                return u;
            }
            else{
                return null;
            }
        }
        return null;
    }
    public void registerUser(String username, String password, String firstName, String lastName, String email){
        User newUser = new User(0, username, password, firstName, lastName, email);
        ud.createUser(newUser);
    }
    public User viewAccount(int id){
        return ud.employeeViewAccountInfoByID(id);
    };
    public User updateUser(User u){
        return ud.employeeUpdateAccountInfo(u);
    }

    public List<User> managerViewUsers(){
        return ud.managerViewAllEmployees();
    }

    public void deleteUser(User u){
        ud.deleteUser(u);
    }

}