package api.tests.task;

import api.methods.BoardMethods;
import api.methods.TaskMethods;
import org.testng.annotations.Test;

import java.util.Random;

public class GetAllTaskTest {
    @Test
    public void getAllTaskTest(){
        BoardMethods.getAllBoards();
        if (BoardMethods.getBoardsIds().size() != 0) {
            TaskMethods.getAllTasks(BoardMethods.getBoardsIds().get(new Random().nextInt(BoardMethods.getBoardsIds().size())));
        }
    }
}
