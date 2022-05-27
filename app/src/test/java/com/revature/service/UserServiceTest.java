package com.revature.service;

import com.revature.dao.IUser;
import com.revature.exceptions.EmailOrPasswordIncorrectException;
import com.revature.models.User;
import com.revature.services.UserService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    @Before
    public void setupBeforeMethods(){
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    static IUser ud;

    @InjectMocks
    static UserService us;

    @Test
    public void registerCreatesNewUser(){
        User u = mock(User.class);
        ud.createUser(u);

        us.registerUser(u.getUsername(), u.getPassword(), u.getFirst_name(), u.getLast_name(), u.getEmail());

        verify(ud).createUser(u);

    }
    @Test
    public void deleteUser(){
        User u = mock(User.class);

        us.deleteUser(u);
        verify(ud).deleteUser(u);
    }

    @Test
    public void validLoginTest() throws EmailOrPasswordIncorrectException {
        User u = new User("Username", "password", "fname", "lname", "test@email.com");

        when(ud.employeeViewAccountInfoByEmail(any())).thenReturn(u);


        User loggedIn = us.loginUser("test@email.com", "password");
        verify(ud).employeeViewAccountInfoByEmail(any());


        assertEquals("The first name should be User", "fname", loggedIn.getFirst_name());

    }
    @Test(expected=EmailOrPasswordIncorrectException.class)
    public void InvalidEmailLoginTest() throws EmailOrPasswordIncorrectException{
        User u = null;
        when(ud.employeeViewAccountInfoByEmail(any())).thenReturn(u);

        User loggedIn = us.loginUser("email@.com", "password");
    }

    @Test(expected=EmailOrPasswordIncorrectException.class)
    public void wrongPasswordTest() throws EmailOrPasswordIncorrectException {
        User u = new User("Username", "pass", "fname", "lname", "test@mail.com");

        when(ud.employeeViewAccountInfoByEmail(any())).thenReturn(u);

        User loggedIn = us.loginUser("test@mail.com", "password");
    }
    @Test
    public void ManagerViewEmployees(){

        us.managerViewUsers();

        verify(ud.managerViewAllEmployees());
    }

}
