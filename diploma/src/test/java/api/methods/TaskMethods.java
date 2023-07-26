package api.methods;

import api.bodys.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import configs.Urls;

import java.util.List;

public class TaskMethods {
    private static String LOGIN_NAME = Urls.getLoginName();
    private static String TOKEN = Urls.getToken();
    private static String API_URL = Urls.getApiUrl();
    private static List<Integer> allTaskIds;
    private static int createdTaskId;

    public static void getAllTasks(int projectId){
        TaskParams taskParams = TaskParams.builder()
                .project_id(projectId)
                .status_id(1)
                .build();

        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(taskParams)
                .method("getAllTasks")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        response.then().statusCode(200);
        allTaskIds = response.jsonPath().getList("result.id");
        response.prettyPrint();
    }

    public static void createTask(String title, int boardId){
        TaskBody taskBody = TaskBody.builder()
                .title(title + "TaskTest")
                .project_id(boardId)
                .build();

        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(taskBody)
                .method("createTask")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);

        response.then().statusCode(200);
        Assert.assertFalse(response.jsonPath().get("error") != null, "Something went wrong. Please check credentials or using method");
        createdTaskId = response.jsonPath().get("result");
        response.prettyPrint();
    }


    public static void deleteTask(int taskId){
        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(new TaskIdBody(taskId))
                .method("removeTask")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        Assert.assertTrue(response.jsonPath().getBoolean("result"), "Task not existed ot can't be deleted");
        response.prettyPrint();
    }

    public static List<Integer> getAllTaskIds() {
        return allTaskIds;
    }

    public static int getCreatedTaskId() {
        return createdTaskId;
    }
}
