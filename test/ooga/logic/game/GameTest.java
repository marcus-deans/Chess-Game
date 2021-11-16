package ooga.logic.game;

import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTest {
    private GameBoard board = new GameBoard(15, 15);
    private Map<String, String> metadata = new HashMap<>();
    private Game game = new Game(board, metadata);

    @Test
    void testGetScoreUpToForOpens () {
        assertEquals(new ArrayList<>(), game.getPossibleCoordinates());
    }

    @Test
    void testGetStandardPossibleCoordinates () {
        List<GameCoordinate> list = new ArrayList<>();
        GameCoordinate sample = new GameCoordinate(5, 5);
        list.add(sample);
        GameCoordinate sample2 = new GameCoordinate(5, 6);
        list.add(sample2);
        game.update(sample);

        assertEquals(new ArrayList<>(), game.getPossibleCoordinates());
    }

    @Test
    void testGetJumpPossibleCoordinates () {
        List<GameCoordinate> list = new ArrayList<>();
        GameCoordinate test = new GameCoordinate(5, 5);
        Boolean tru = board.getIsJump(test);
        if(tru){
            game.update(test);
        }
        list.add(test);

        assertEquals(new ArrayList<>(), game.getPossibleCoordinates());
    }
}

