package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementJDBC implements IReimbursement {

    private ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();

    @Override
    public void employeeCreateReimbursement(Reimbursement r) {


        Connection c = cs.getConnection();

        // creates a new reimbursement ticket
        String sql = "insert into reimbursement (amount, submitted_date, description, reimbursement_author, reimbursement_status, reimbursement_type) values" +
                "('" + r.getAmount() + "','" +
                r.getSubmitted_date() + "','" +
                r.getDescription() + "','" +
                r.getReimbursement_author() + "','" +  //+ "')";
                r.getReimbursement_status() + "','" +
                r.getReimbursement_type()+"')";

        try{
            Statement s = c.createStatement();
            s.execute(sql);
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reimbursement> employeeViewAllPending( int i) {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewEmployeePending(?)}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, i);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<Reimbursement> employeesPending = new ArrayList<>();

            while(rs.next()) {
                Reimbursement vp = new Reimbursement(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9));
                employeesPending.add(vp);
            }
            return employeesPending;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Reimbursement> employeeViewAllResolved(int i) {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewEmployeeResolved(?)}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, i);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<Reimbursement> employeesPending = new ArrayList<>();

            while(rs.next()) {
                Reimbursement vp = new Reimbursement(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9));
                employeesPending.add(vp);
            }
            return employeesPending;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public Reimbursement managerApproveRequest(int manager, int user) {
        Connection c = cs.getConnection();

        String sql = "update reimbursement set reimbursement_resolver = "+manager + ", reimbursement_status = 2, resolved_date = current_date where reimbursement_id = " + user;

        try{
            Statement s = c.createStatement();
            s.execute(sql);
            c.setAutoCommit(false);
            String sql1 = "{?=call approveRequest(?, ?)}";

            CallableStatement call = c.prepareCall(sql1);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, manager);
            call.setInt(3,user);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);


            Reimbursement ar = null;
            while(rs.next()) {
                ar = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
            }
            return ar;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reimbursement managerDenyRequest(int manager, int user) {
        Connection c = cs.getConnection();
        String sql = "update reimbursement set reimbursement_resolver = "+manager + ", reimbursement_status = 3, resolved_date = current_date where reimbursement_id = " + user;

        try{
            Statement s = c.createStatement();
            s.execute(sql);
            c.setAutoCommit(false);
            String sql1 = "{?=call denyRequest(?, ?)}";

            CallableStatement call = c.prepareCall(sql1);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, manager);
            call.setInt(3,user);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);


            Reimbursement dr = null;
            while(rs.next()) {
                dr = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
            }

            return dr;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reimbursement> managerViewAllPending() {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAllPending()}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<Reimbursement> viewAllPending = new ArrayList<>();

            while(rs.next()) {
                Reimbursement vAP = new Reimbursement(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9));
                viewAllPending.add(vAP);
            }
            return viewAllPending;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reimbursement> managerViewAllResolved() {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAllResolved()}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<Reimbursement> viewAllResolved = new ArrayList<>();

            while(rs.next()) {
                Reimbursement vAR = new Reimbursement(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9));
                viewAllResolved.add(vAR);
            }
            return viewAllResolved;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reimbursement> managerViewAllByEmployee(int id) {
        Connection c = cs.getConnection();

        try{
            c.setAutoCommit(false);
            String sql = "{?=call viewAllOfEmployee(?)}";

            CallableStatement call = c.prepareCall(sql);

            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, id);
            call.execute();

            ResultSet rs = (ResultSet) call.getObject(1);

            List<Reimbursement> viewAllByEmployee = new ArrayList<>();

            while(rs.next()) {
                Reimbursement vABE = new Reimbursement(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9));
                viewAllByEmployee.add(vABE);
            }
            return viewAllByEmployee;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}