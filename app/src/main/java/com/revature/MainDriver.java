package com.revature;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.dao.IReimbursement;
import com.revature.dao.IUser;
import com.revature.dao.UserJDBC;
import com.revature.dao.ReimbursementJDBC;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;

public class MainDriver {

    public static void main(String[] args) {

        IUser ud = new UserJDBC();
        IReimbursement rd = new ReimbursementJDBC();

        ReimbursementService rs = new ReimbursementService(rd);
        UserService us = new UserService(ud, rd);

        UserController uc = new UserController(us);
        ReimbursementController rc = new ReimbursementController(rs, us);

        Javalin server = Javalin.create(config ->{
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.enableCorsForAllOrigins();
        });

        server.before(ctx -> ctx.header("Access-Control-Allow-Credentials", "true"));
        server.before(ctx -> ctx.header("Access-Control-Expose-Headers", "*"));
        server.routes(()-> {
            path("project1", () -> {
                post("/createAccount", uc.handleCreateAccount);
                post("/login", uc.handleLogin);
                get("/logout", uc.handleLogout);
                get("/viewAccount", uc.handleViewAccount);
                put("/update", uc.handleUpdateUser);
                get("/managerViewUsers", uc.handleViewAllUserByManager);
                delete("/{id}", uc.handleDeleteUser);

            });
            path("reimbursement", () -> {
                post("/createReimbursement", rc.handleCreateReimbursement);
                get("/employeeViewPending", rc.handleEmployeeViewPending);
                get("/employeeViewResolved", rc.handleEmployeeViewResolved);
                get("/managerApprove", rc.handleManagerApprove);
                get("/managerDeny", rc.handleManagerDeny);
                get("/managerViewAllPending", rc.handleManagerAllPending);
                get("/managerViewAllResolved", rc.handleManagerAllResolved);
                get("/managerViewByEmployee", rc.handleManagerByEmployee);
            });
        });

        server.start(8000);






    }

}