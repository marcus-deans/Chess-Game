package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.Board;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private GameBoard myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;
    private GameCoordinate selected;
    private List<Integer> scores;
    private GameSpot selectedSpot;
    private List<Coordinate> possibleCoordinates;

    public Game(GameBoard board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
        this.possibleCoordinates = new ArrayList<>();
    }

    private List<Coordinate> getJumpPossibleCoordinate(List<Coordinate> list){
        for(int i = 0; i < list.size(); i++){
            if(myBoard.hasPiece(list.get(i))){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    private List<Coordinate> getStandardPossibleCoordinate(List<Coordinate> list){
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

        List<Coordinate> possiblePositions = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getY_pos() < maxY && list.get(i).getY_pos() > minY
            && list.get(i).getX_pos() < maxX && list.get(i).getX_pos() > minX
            ){
                possiblePositions.add(list.get(i));
            }
        }

        return possiblePositions;
    }

    public void searchPossiblePositions(GameCoordinate selected){
        this.selectedSpot = myBoard.getSpot(selected);

        List<Coordinate> possiblePositions = selectedSpot.getPiece().getPossibleMoves();


        Boolean isJump = myBoard.getIsJump(selected);

        if(isJump) possibleCoordinates =  getJumpPossibleCoordinate(possiblePositions);
        else possibleCoordinates = getStandardPossibleCoordinate(possiblePositions);
    }

    public List<Coordinate> getPossibleCoordinates(GameCoordinate selected){
        searchPossiblePositions(selected);

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

    public void resetClick(){

    }

}
