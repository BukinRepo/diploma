package ui.tests;

import api.methods.BoardMethods;
import api.methods.UserMethods;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;
import ui.methods.LogInPageMethods;
import ui.methods.NewProjectMethods;
import utils.RandomGenerator;

public class NewProjectTest {
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
    public void createNewProjectTest(){
        userManagement();
        NewProjectMethods projectMethods = new NewProjectMethods();
        projectMethods.projectCreator(false);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(){
        UserMethods.deleteUser(userId);
        String currentUrl = WebDriverRunner.url();
        int lastSlashIndex = currentUrl.lastIndexOf("/");
        currentUrl = currentUrl.substring(lastSlashIndex + 1);
        BoardMethods.deleteBoard(Integer.parseInt(currentUrl));
    }

    private void userManagement() {
        LogInPageMethods logInPageMethods = new LogInPageMethods();
        logInPageMethods.logInByUser(userName, password);
    }

    private void userRole(){
        UserMethods.setRole("app-manager");
        UserMethods.createUser(userName, password);
        this.userId = UserMethods.getCreatedUserId();
        this.userName = userName + "Test";
    }


}
