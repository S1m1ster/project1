package com.revature.dao;

import com.revature.models.User;
import com.revature.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC implements IUser {

    public ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();

    @Override
    public void createUser(User u){
        Connection c = cs.getConnection();

        String sql = "insert into users (username, password, first_name, last_name, email, user_role)" +
                "values" +
                "('" + u.getUsername() +
                "','" + u.getPassword() +
                "','" + u.getFirst_name() +
                "','" + u.getLast_name() +
                "','" + u.getEmail() +
                "','" + 2 + "')";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User employeeViewAccountInfoByID(int id) {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAccountByID(?)}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, id);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);
            User u = null;
            while(rs.next()) {
                u = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));

            }
            return u;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public User employeeViewAccountInfoByEmail(String email) {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAccountByEmail(?)}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.setString(2, email);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);
            User u = null;
            while(rs.next()) {
                u = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));

            }

            return u;
        }
        catch(SQLException e){

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User employeeUpdateAccountInfo(User u) {
        Connection c = cs.getConnection();

        String sql = "UPDATE users " +
                "SET username = ?," +
                "password = ?," +
                "first_name = ?," +
                "last_name = ?," +
                "email = ?" +
                "WHERE user_id = ?";
        try{
            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, u.getUsername());
            p.setString(2, u.getPassword());
            p.setString(3, u.getFirst_name());
            p.setString(4, u.getLast_name());
            p.setString(5, u.getEmail());
            p.setInt(6, u.getUser_id());
            p.execute();
            return u;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> managerViewAllEmployees() {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAllEmployees()}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<User> viewAllEmployees = new ArrayList<>();

            while(rs.next()) {
                User u = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                viewAllEmployees.add(u);
            }
            return viewAllEmployees;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void deleteUser(User u){
        Connection c = cs.getConnection();

        String sql = "DELETE FROM users WHERE user_id = ?";

        try{
            PreparedStatement p = c.prepareStatement(sql);

            p.setInt(1, u.getUser_id());

            p.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
