package ui.tests;

import api.methods.BoardMethods;
import api.methods.UserMethods;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.methods.AddCommentInTaskMethods;
import ui.methods.CreateAndCloseTaskMethods;
import ui.methods.LogInPageMethods;
import ui.methods.NewProjectMethods;
import utils.RandomGenerator;

public class AddCommentInTaskTest {
    private String userName;
    private String password;
    private String taskTitle;
    private int taskNum;
    private int userId;
    private String boardId;
    private String randomComment;
    @BeforeMethod
    public void prepareUserBeforeTest(){
        this.userName = new RandomGenerator().stringGen(7);
        this.password = new RandomGenerator().stringGen(9);
        this.taskTitle = new RandomGenerator().stringGen(12);
        this.randomComment = new RandomGenerator().stringGen(30);
        userTaskAndProjectManagement();
    }

    @Test
    public void addCommentTest(){
        AddCommentInTaskMethods addCommentInTaskMethods = new AddCommentInTaskMethods();
        addCommentInTaskMethods.addComment(boardId, taskTitle, taskNum, randomComment);
    }


    @AfterMethod(alwaysRun = true)
    public void cleanUp(){
        UserMethods.deleteUser(userId);
        BoardMethods.deleteBoard(Integer.parseInt(boardId));
    }

    private void userTaskAndProjectManagement(){
        UserMethods.setRole("app-manager");
        UserMethods.createUser(userName, password);
        this.userId = UserMethods.getCreatedUserId();
        this.userName = userName + "Test";
        LogInPageMethods logInPageMethods = new LogInPageMethods();
        logInPageMethods.logInByUser(userName, password);
        NewProjectMethods projectMethods = new NewProjectMethods();
        projectMethods.projectCreator(false);
        this.boardId = projectMethods.getProjectId();
        CreateAndCloseTaskMethods createTaskMethods = new CreateAndCloseTaskMethods();
        createTaskMethods.taskCreator(taskTitle, boardId);
        this.taskNum = createTaskMethods.getTaskNum();
    }
}
