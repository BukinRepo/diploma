package api.methods;

import api.bodys.ArgBodyParams;
import api.bodys.UserBody;
import api.bodys.UserIdBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import configs.Urls;

import java.util.List;

public class UserMethods {
    private static String LOGIN_NAME = Urls.getLoginName();
    private static String TOKEN = Urls.getToken();
    private static String API_URL = Urls.getApiUrl();
    private static List<String> usersName;
    private static int createdUserId;
    private static String role = "app-user";
    public static void getAllUsers(){
        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .method("getAllUsers")
                .build();


        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        response.then().statusCode(200);
        usersName = response.jsonPath().getList("result.username");
        response.prettyPrint();
    }


    public static void createUser(String userName, String password){
        UserBody userData = UserBody.builder()
                .username(userName + "Test")
                .password((password))
                .name(userName + "Test")
                .email(userName + "@email.com")
                .role(role)
                .build();

        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(userData)
                .method("createUser")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);

        response.then().statusCode(200);
        Assert.assertFalse(response.jsonPath().get("error") != null, "Something went wrong. Please check credentials or using method");
        createdUserId = response.jsonPath().get("result");
        response.prettyPrint();
    }

    public static void deleteUser(int userId){
        ArgBodyParams argBodyParams = ArgBodyParams.builder()
                .params(new UserIdBody(userId))
                .method("removeUser")
                .build();

        Response response = RestAssured.given()
                .auth().basic(LOGIN_NAME, TOKEN)
                .body(argBodyParams)
                .when()
                .post(API_URL);
        Assert.assertTrue(response.jsonPath().getBoolean("result"), "User not existed ot can't be deleted");
        response.prettyPrint();
    }


    public static List<String> getUsersName() {
        return usersName;
    }

    public static int getCreatedUserId() {
        return createdUserId;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserMethods.role = role;
    }
}
