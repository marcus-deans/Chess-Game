package ooga.logic.game;

import ooga.logic.board.Board;
import ooga.logic.board.GameBoard;

import java.util.Map;

public class Game {
    private GameBoard myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;


    public Game(GameBoard board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
    }

    protected void makeBoard(GameBoard newBoard){
        this.myBoard = newBoard;
    }

    public Board getBoard() {
        return myBoard;
    }

    /**
     * Returns the metadata of the game.
     *
     * @return the metadata of the game.
     */
    public Map<String, String> getMetaData() {
        return metadata;
    }


}
