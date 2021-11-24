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

}

