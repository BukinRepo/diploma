package api.tests.user;

import api.methods.UserMethods;
import org.testng.annotations.Test;

public class GetAllUsersTest {
    @Test
    public void getAllUsersTest() {
        UserMethods.getAllUsers();
    }
}
