package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginObject;
import com.revature.models.User;
import com.revature.models.CreateUser;
import com.revature.services.UserService;
import io.javalin.http.Handler;

public class UserController {
    private UserService us;
    private ObjectMapper om;

    public UserController(UserService us){
        this.us = us;
        this.om = new ObjectMapper();
    }
    public Handler handleCreateAccount = (ctx) -> {
        CreateUser cu = om.readValue(ctx.body(), CreateUser.class);
        System.out.println(cu);
        us.registerUser(cu.username, cu.password, cu.first_name, cu.last_name, cu.email);
        ctx.status(201);
        ctx.result("User has been created");
    };
    public Handler handleLogin = (ctx) -> {
        ctx.header("Access-Control-Expose-Headers", "*");
        LoginObject lo = om.readValue(ctx.body(), LoginObject.class);

        User u = us.loginUser(lo.email, lo.password);

        if(u == null){
            ctx.status(401);
            ctx.result("Username or password was incorrect");
        }
        else{
            ctx.req.getSession().setAttribute("loggedIn: ", u.getEmail());
            ctx.req.getSession().setAttribute("id", "" + u.getUser_id());
            ctx.result(om.writeValueAsString(u.getFirst_name() +" "+ u.getLast_name() + " has logged in"));
        }
    };

    public Handler handleLogout = (ctx) -> {
        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.req.getSession().invalidate();
        ctx.result("User logged out");
    };
    public Handler handleViewAccount = (ctx) -> {
        ctx.header("Access-Control-Expose-Headers", "*");
        if(ctx.req.getSession().getAttribute("id") == null){
            ctx.status(403);
            ctx.result("Must be logged in to view account");
        }
        else{
            int user = Integer.parseInt((String) ctx.req.getSession().getAttribute("id"));
            ctx.result(om.writeValueAsString(us.viewAccount(user)));
        }
    };

    public Handler handleUpdateUser = (ctx) -> {
        if(ctx.req.getSession().getAttribute("id") == null){
            ctx.status(403);
            ctx.result("Must be logged in to update account");
        }
        else{
            User u = om.readValue((ctx.body()), User.class);
            ctx.req.getSession().setAttribute("id", "" + u.getUser_id());
            ctx.result(om.writeValueAsString(us.updateUser(u)));
        }
    };

    public Handler handleViewAllUserByManager = (ctx) -> {
        User u = new User();
        if(ctx.req.getSession().getAttribute("id") == null){
            ctx.status(403);
            ctx.result("Must be logged in to update account");
        }
        else{
            u = us.viewAccount(Integer.parseInt((String) ctx.req.getSession().getAttribute("id")));
            if(u.getUser_role() !=2 ){
                ctx.status(401);
                ctx.result("Must be manager to view all employees");
            }
            else{
                ctx.result(om.writeValueAsString(us.managerViewUsers()));
            }
        }
    };

    public Handler handleDeleteUser = (ctx) -> {
        if(ctx.req.getSession().getAttribute("id") == null){
            ctx.status(403);
            ctx.result("Must be logged in to delete account");
        }
        else{
            int id = Integer.parseInt(ctx.pathParam("id"));
            User u = new User();
            u.setUser_id(id);
            us.deleteUser(u);
            ctx.result("User deleted");
        }
    };

}