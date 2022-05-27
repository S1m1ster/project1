package com.revature.services;

import com.revature.dao.IReimbursement;
import com.revature.models.Reimbursement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ReimbursementService {

    private IReimbursement ir;

    public ReimbursementService(IReimbursement ir) {
        this.ir = ir;
    }


    //-----------------------------------Employees----------------------------------------

    // replace int employee with user
    public void createReimbursement(int amount, String description, int employee, int rt){
        int status = 1;
        Date today = Date.valueOf(LocalDate.now());
        Reimbursement r = new Reimbursement(amount, today, description, employee, status, rt);

        ir.employeeCreateReimbursement(r);
    }

    public List<Reimbursement> employeeViewPending(int id){
        return ir.employeeViewAllPending(id);
    }

    public List<Reimbursement> employeeViewResolved(int id){
        return ir.employeeViewAllResolved(id);
    }


    //-----------------------------------Manager----------------------------------------

    // replace both int with user
    public Reimbursement managerApprove(int manager, int employee){
        return ir.managerApproveRequest(manager, employee);
    }
    // replace both int with users
    public  Reimbursement managerDeny(int manager, int employee){

        return ir.managerDenyRequest(manager, employee);
    }
    public List<Reimbursement> managerAllPending(){
        return ir.managerViewAllPending();
    }
    public List<Reimbursement> managerAllResolved(){
        return ir.managerViewAllResolved();
    }
    // replace int with user
    public List<Reimbursement> managerByEmployee(int employee){
        return ir.managerViewAllByEmployee(employee);
    }

}
