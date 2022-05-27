package com.revature.service;

import com.revature.dao.IReimbursement;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


public class ReimbursementServiceTest {
    @Before
    public void setupBeforeMethods(){
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    static IReimbursement rd;

    @InjectMocks
    static ReimbursementService rs;

    @Test
    public void CreateReimbursement(){
        Reimbursement r = mock(Reimbursement.class);
        rd.employeeCreateReimbursement(r);

        rs.createReimbursement(r.getAmount(),r.getDescription(),r.getReimbursement_author(),r.getReimbursement_type());

        verify(rd).employeeCreateReimbursement(r);

    }
    @Test
    public void ManagerApproveRequest(){
        Reimbursement r = mock(Reimbursement.class);

        rs.managerApprove(r.getReimbursement_resolver(), r.getReimbursement_id());

        verify(rd).managerApproveRequest(r.getReimbursement_resolver(), r.getReimbursement_id());
    }

    @Test
    public void ManagerDenyRequest(){
        Reimbursement r = mock(Reimbursement.class);

        rs.managerDeny(r.getReimbursement_resolver(), r.getReimbursement_id());

        verify(rd).managerDenyRequest(r.getReimbursement_resolver(), r.getReimbursement_id());
    }

}
