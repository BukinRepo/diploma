package api.tests.user;

import api.methods.UserMethods;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateAndDeleteUserTest {
    private String name;
    private String password;
    private int userId = -1;

    @Test
    public void createUser() {
        UserMethods.getAllUsers();
        RandomGenerator generator = new RandomGenerator();
        this.name = generator.stringGen(10);
        for (String nameChecker : UserMethods.getUsersName()) {
            if (this.name == nameChecker) {
                createUser();
            }
        }
        this.password = generator.stringGen(7);
        UserMethods.createUser(this.name, this.password);
        userId = UserMethods.getCreatedUserId();
    }

    @Test
    public void deleteUser(){
        UserMethods.getAllUsers();
        if(UserMethods.getUsersName().size() != 0 && userId != -1){
            UserMethods.deleteUser(userId);
        } else {
            createUser();
            deleteUser();
        }
    }
}
