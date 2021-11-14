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
            if(myBoard.hasPiece(list.get(i))){
                list.remove(i);
                i--;
            }
        }

    }

    private List<GameCoordinate> getStandardPossibleCoordinate(List<GameCoordinate> list){
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(myBoard.hasPiece(list.get(i))){
                int posX = list.get(i).getX_pos();
                int posY = list.get(i).getY_pos();
                if(selected.getX_pos() < posX && posX < maxX){
                    maxX = posX;
                }else if(selected.getX_pos() > posX && posX > minX){
                    minX = posX;
                }
                if(selected.getY_pos() < posY && posY < maxY){
                    maxY = posY;
                }else if(selected.getY_pos() > posY && posY > minY){
                    minY = posY;
                }
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
