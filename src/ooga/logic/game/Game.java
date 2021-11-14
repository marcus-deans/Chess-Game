package ooga.logic.game;

import ooga.logic.board.board.Board;

import java.util.Map;

public class Game {
    private Board myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;


    public Game(Board board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
    }

    protected void makeBoard(Board newBoard){
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
