package com.revature.models;

import java.sql.Date;

public class Reimbursement {
    private int reimbursement_id;
    private int amount;
    private Date submitted_date;
    private Date resolved_date;
    private String description;
    private int reimbursement_author;
    private int reimbursement_resolver;
    private int reimbursement_status;
    private int reimbursement_type;

    private User u;

    public Reimbursement() {
    }

    public Reimbursement(User u) {
        this.reimbursement_author = u.getUser_id();
    }

    //manager getting reimbursement id to approve or deny
    public Reimbursement(int id){
        this.reimbursement_id = id;
    }


    //user input
    public Reimbursement(int amount, String description, int reimbursement_type) {
        this.amount = amount;
        this.description = description;
        this.reimbursement_type = reimbursement_type;
    }
    //reimbursementController input
    public Reimbursement(int amount, String description, int reimbursement_author, int reimbursement_type) {
        this.amount = amount;
        this.submitted_date = submitted_date;
        this.description = description;
        this.reimbursement_author = reimbursement_author;
        this.reimbursement_type = reimbursement_type;
    }

    //reimbursement service
    public Reimbursement(int amount, Date submitted_date, String description, int reimbursement_author, int reimbursement_status, int reimbursement_type) {
        this.amount = amount;
        this.submitted_date = submitted_date;
        this.description = description;
        this.reimbursement_author = reimbursement_author;
        this.reimbursement_status = reimbursement_status;
        this.reimbursement_type = reimbursement_type;
    }

    // full args
    public Reimbursement(int reimbursement_id, int amount, Date submitted_date, Date resolved_date, String description, int reimbursement_author, int reimbursement_resolver, int reimbursement_status, int reimbursement_type) {
        this.reimbursement_id = reimbursement_id;
        this.amount = amount;
        this.submitted_date = submitted_date;
        this.resolved_date = resolved_date;
        this.description = description;
        this.reimbursement_author = reimbursement_author;
        this.reimbursement_resolver = reimbursement_resolver;
        this.reimbursement_status = reimbursement_status;
        this.reimbursement_type = reimbursement_type;
    }

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public  Date getSubmitted_date() {
        return submitted_date;
    }

    public void setSubmitted_date( Date submitted_date) {
        this.submitted_date = submitted_date;
    }

    public  Date getResolved_date() {
        return resolved_date;
    }

    public void setResolved_date( Date resolved_date) {
        this.resolved_date = resolved_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDecription(String decription) {
        this.description = decription;
    }

    public int getReimbursement_author() {
        return reimbursement_author;
    }

    public void setReimbursement_author(int reimbursement_author) {
        this.reimbursement_author = reimbursement_author;
    }

    public int getReimbursement_resolver() {
        return reimbursement_resolver;
    }

    public void setReimbursement_resolver(int reimbursement_resolver) {
        this.reimbursement_resolver = reimbursement_resolver;
    }

    public int getReimbursement_status() {
        return reimbursement_status;
    }

    public void setReimbursement_status(int reimbursement_status) {
        this.reimbursement_status = reimbursement_status;
    }

    public int getReimbursement_type() {
        return reimbursement_type;
    }

    public void setReimbursement_type(int reimbursement_type) {
        this.reimbursement_type = reimbursement_type;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursement_id=" + reimbursement_id +"\n"+
                ", amount=" + amount + "\n"+
                ", submitted_date=" + submitted_date + "\n"+
                ", resolved_date=" + resolved_date + "\n"+
                ", decription='" + description + '\'' + "\n"+
                ", reimbursement_author=" + reimbursement_author + "\n"+
                ", reimbursement_resolver=" + reimbursement_resolver + "\n"+
                ", reimbursement_status=" + reimbursement_status + "\n"+
                ", reimbursement_type=" + reimbursement_type + "\n"+ "\n"+"\n"+
                '}';
    }
}