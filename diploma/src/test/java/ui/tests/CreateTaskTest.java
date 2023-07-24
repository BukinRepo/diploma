package ui.tests;

import api.methods.UserMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateTaskTest {
    private String userName;
    private String password;
    private int userId;
    @BeforeMethod
    public void prepareUserBeforeTest(){
        this.userName = new RandomGenerator().stringGen(7);
        this.password = new RandomGenerator().stringGen(9);
        userRole();
    }

    @Test
    public void createTask(){

    }

    private void userRole(){
        UserMethods.setRole("app-manager");
        UserMethods.createUser(userName, password);
        this.userId = UserMethods.getCreatedUserId();
        this.userName = userName + "Test";
    }
}
