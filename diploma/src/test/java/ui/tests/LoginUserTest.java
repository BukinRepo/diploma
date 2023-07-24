package ui.tests;

import api.methods.UserMethods;
import org.testng.annotations.*;
import ui.methods.LogInPageMethods;

public class LoginUserTest {
    private String userName = "UserForUITest122";
    private String password = "9876543210";
    private int userId;

@BeforeMethod
    public void prepareUserBeforeTest(){
        UserMethods.createUser(userName, password);
        this.userId = UserMethods.getCreatedUserId();
        this.userName = userName + "Test";
    }

@Test
    public void loginUserTest(){
        LogInPageMethods logInPageMethods = new LogInPageMethods();
        logInPageMethods.logInByUser(userName, password);
        logInPageMethods.successLogin();
    }
@Test
    public void loginUserWrongUserNameTest(){
        LogInPageMethods logInPageMethods = new LogInPageMethods();
        logInPageMethods.logInByUser(userName + "1", password);
        logInPageMethods.checkLoginError();
    }
@Test
    public void loginUserWrongPasswordTest(){
        LogInPageMethods logInPageMethods = new LogInPageMethods();
        logInPageMethods.logInByUser(userName, password + "1");
        logInPageMethods.checkLoginError();
    }


@AfterMethod(alwaysRun = true)
    public void deleteUser(){
        UserMethods.deleteUser(userId);
    }
}
