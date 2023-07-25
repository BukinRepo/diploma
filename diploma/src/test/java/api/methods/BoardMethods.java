package api.methods;

import api.bodys.ArgBodyParams;
import api.bodys.BoardIdBody;
import api.bodys.BoardNameBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import configs.Urls;

import java.util.List;

public class BoardMethods {
    private static String LOGIN_NAME = Urls.getLoginName();
    private static String TOKEN = Urls.getToken();
    private static String API_URL = Urls.getApiUrl();
    private static List<Integer> boardsIds;
    private static List<String> boardsName;
    private static int createdBoardId;

    public static void getAllBoards(){
        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .method("getAllProjects")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        response.then().statusCode(200);
        boardsIds = response.jsonPath().getList("result.id");
        boardsName = response.jsonPath().getList("result.name");
        response.prettyPrint();
    }

    public static void createBoard(String boardName){
        BoardNameBody boardNameBody = BoardNameBody.builder()
                .name(boardName + "Test")
                .build();

        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(boardNameBody)
                .method("createProject")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);

        response.then().statusCode(200);
        Assert.assertFalse(response.jsonPath().get("error") != null, "Something went wrong. Please check credentials or using method");
        createdBoardId = response.jsonPath().get("result");
        response.prettyPrint();
    }

    public static void deleteBoard(int projectId){
        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(new BoardIdBody(projectId))
                .method("removeProject")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        Assert.assertTrue(response.jsonPath().getBoolean("result"), "Board not existed ot can't be deleted");
        response.prettyPrint();
    }


    public static List<String> getBoardsName() {
        return boardsName;
    }

    public static int getCreatedBoardId() {
        return createdBoardId;
    }

    public static List<Integer> getBoardsIds() {
        return boardsIds;
    }
}
