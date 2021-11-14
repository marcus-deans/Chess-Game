package ooga.logic.game;

import ooga.logic.board.board.Board;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

import java.util.List;
import java.util.Map;

public class Game {
    private Board myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;
    private GameCoordinate selected;


    public Game(Board board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
    }

    private List<GameCoordinate> getJumpPossibleCoordinate(List<GameCoordinate> list){
        for(int i = 0; i < list.size(); i++){
            if(myBoard.checkPieceExists(list.get(i))){
                list.remove(i);
                i--;
            }
        }

    }
    private List<GameCoordinate> getStandardPossibleCoordinate(List<GameCoordinate> list){
        for(int i = 0; i < list.size(); i++){
            if(myBoard.checkPieceExists(list.get(i))){
                list.remove(i);
                i--;
            }
        }

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
