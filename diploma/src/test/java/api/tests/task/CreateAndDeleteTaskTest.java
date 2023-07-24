package api.tests.task;

import api.methods.BoardMethods;
import api.methods.TaskMethods;
import org.testng.annotations.Test;
import utils.RandomGenerator;

import java.util.List;
import java.util.Random;

public class CreateAndDeleteTaskTest {
    private int taskId = -1;
    private int boardId = -1;

    @Test
    public void createTaskTest(){
        BoardMethods.getAllBoards();
        if (BoardMethods.getBoardsIds().size() != 0) {
            RandomGenerator generator = new RandomGenerator();
            boardIsRandomChecker();
            TaskMethods.createTask(generator.stringGen(7), this.boardId);
            this.taskId = TaskMethods.getCreatedTaskId();
        } else {
            ifNoBoard();
            createTaskTest();
        }
    }

    @Test
    public void deleteTaskTest() {
        BoardMethods.getAllBoards();
        if (BoardMethods.getBoardsIds().size() != 0) {
            boardIsRandomChecker();
            TaskMethods.getAllTasks(boardId);
            taskProcessing();
        } else {
            ifNoBoard();
            deleteTaskTest();
        }
    }

    private void taskProcessing(){
        if (TaskMethods.getAllTaskIds().size() != 0 && taskId != -1) {
                TaskMethods.deleteTask(taskId);
        } else {
            createTaskTest();
            deleteTaskTest();
        }
    }

    private void boardIsRandomChecker(){
        if(this.boardId == -1) {
            this.boardId = BoardMethods.getBoardsIds().get(new Random().nextInt(BoardMethods.getBoardsIds().size()));
        }
    }

    private void ifNoBoard(){
        RandomGenerator generator = new RandomGenerator();
        BoardMethods.createBoard(generator.stringGen(10));
    }

}
