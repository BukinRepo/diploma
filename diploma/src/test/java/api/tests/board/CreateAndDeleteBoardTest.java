package api.tests.board;

import api.methods.BoardMethods;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateAndDeleteBoardTest {
    private String boardName;
    private int boardId = -1;
    @Test
    public void createBoardTest(){
        BoardMethods.getAllBoards();
        RandomGenerator generator = new RandomGenerator();
        this.boardName = generator.stringGen(10);
        for (String boardNameChecker : BoardMethods.getBoardsName()){
            if (this.boardName == boardNameChecker){
                createBoardTest();
            }
        }
        BoardMethods.createBoard(this.boardName);
        this.boardId = BoardMethods.getCreatedBoardId();
    }

    @Test
    public void deleteBoardTest(){
        BoardMethods.getAllBoards();
        if (BoardMethods.getBoardsIds().size() != 0 && this.boardId != -1) {
            BoardMethods.deleteBoard(this.boardId);
        } else {
            createBoardTest();
            deleteBoardTest();
        }
    }
}
