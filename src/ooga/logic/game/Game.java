package ooga.logic.game;

import ooga.logic.board.board.Board;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private GameBoard myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;
    private GameCoordinate selected;
    private List<Integer> scores;
    private List<GameCoordinate> possibleCoordinates;


    public Game(GameBoard board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
        this.possibleCoordinates = new ArrayList<>();
    }

    private List<GameCoordinate> getJumpPossibleCoordinate(List<GameCoordinate> list){
        for(int i = 0; i < list.size(); i++){
            if(myBoard.hasPiece(list.get(i))){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    private List<GameCoordinate> getStandardPossibleCoordinate(List<GameCoordinate> list){
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(myBoard.hasPiece(list.get(i))) {
                int posX = list.get(i).getX_pos();
                int posY = list.get(i).getY_pos();
                if (selected.getX_pos() < posX && posX < maxX) {
                    maxX = posX;
                } else if (selected.getX_pos() > posX && posX > minX) {
                    minX = posX;
                }
                if (selected.getY_pos() < posY && posY < maxY) {
                    maxY = posY;
                } else if (selected.getY_pos() > posY && posY > minY) {
                    minY = posY;
                }
            }
        }

        List<GameCoordinate> possiblePositions = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getY_pos() < maxY && list.get(i).getY_pos() > minY
            && list.get(i).getX_pos() < maxX && list.get(i).getX_pos() > minX
            ){
                possiblePositions.add(list.get(i));
            }
        }

        return possiblePositions;
    }

    public void update(GameCoordinate selected){
        this.selected = selected;

        List<GameCoordinate> possiblePositions = myBoard.getPossibleCoordinates(selected);

        Boolean isJump = myBoard.getIsJump(selected);

        if(isJump) possibleCoordinates =  getJumpPossibleCoordinate(possiblePositions);
        else possibleCoordinates = getStandardPossibleCoordinate(possiblePositions);
    }

    public List<GameCoordinate> getPossibleCoordinates(){
        return possibleCoordinates;
    }

    protected void makeBoard(GameBoard newBoard){
        this.myBoard = newBoard;
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
