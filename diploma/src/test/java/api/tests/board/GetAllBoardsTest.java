package api.tests.board;

import api.methods.BoardMethods;
import org.testng.annotations.Test;

public class GetAllBoardsTest {
    @Test
    public void getAllBoardsTest(){
        BoardMethods.getAllBoards();
    }

}
