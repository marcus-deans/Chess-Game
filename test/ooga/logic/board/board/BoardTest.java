package ooga.logic.board.board;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class BoardTest {
    @Test
    public void boardSetupKingsAndPawns() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[][] a={{"K1","K2"},{"P1","P2"}};
        Board b=new GameBoard(2,2);
        b.setupBoard(a);
    }
}
