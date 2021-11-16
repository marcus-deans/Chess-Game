package ooga.logic.game;

import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameTest {

    @Test
    void testGetScoreUpToForOpens () {
        GameBoard board = new GameBoard(15, 15);
        Map<String, String> metadata = new HashMap<>();
        Game game = new Game(board, metadata);

        game.getPossibleCoordinates();
        assertEquals(new ArrayList<GameCoordinate>(), game.getPossibleCoordinates());
    }
}

}
