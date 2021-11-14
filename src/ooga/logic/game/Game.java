package ooga.logic.game;

import ooga.logic.board.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private GameBoard myBoard;
    private Map<String, GameSpot> boardMap;
    private int boardWidth, boardHeight;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;

    public Game(){

    }

    public Game(GameBoard board,  Map<String, String> metadata, int boardWidth, int boardHeight){
        makeBoard(board);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.metadata = metadata;
    }

    private List<Coordinate> getPossiblePositions(List<GameCoordinate> list, GameCoordinate selected, Set<Coordinate> occupied){

        for(int i = 0; i < list.size(); i++){
            if(occupied.contains(list.get(i))){
                if(selected.getY_pos() < list.get(i).getY_pos()){

                }
            }
        }
    }

    public void update(){

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
