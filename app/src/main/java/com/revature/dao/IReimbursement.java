package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface IReimbursement {

    public void employeeCreateReimbursement(Reimbursement r);

    public List<Reimbursement> employeeViewAllPending(int i);

    public List<Reimbursement> employeeViewAllResolved(int id);

    public Reimbursement managerApproveRequest(int manager, int user);

    public Reimbursement managerDenyRequest(int manager, int user);

    public List<Reimbursement> managerViewAllPending();

    public List<Reimbursement> managerViewAllResolved();

    public List<Reimbursement> managerViewAllByEmployee(int id);
}
