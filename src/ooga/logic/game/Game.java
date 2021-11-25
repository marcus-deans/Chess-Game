package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.Board;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.util.*;

public class Game {
    private GameBoard myBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;
    private GameCoordinate selected;
    private List<Player> players;
    private GameSpot selectedSpot;
    private List<Coordinate> possibleCoordinates;
    private boolean isGameOver;

    public Game(GameBoard board,  Map<String, String> metadata){
        makeBoard(board);
        this.metadata = metadata;
        this.possibleCoordinates = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setSelected(GameCoordinate select){
        selected = select;
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

    private void addCapturePositions(List<Coordinate> possibleCapture){
        for(Coordinate current : possibleCapture){
            if(myBoard.hasPiece(current)) possibleCoordinates.add(current);
        }
    }

    public void searchPossiblePositions(GameCoordinate selected){
        if (myBoard.getSpot(selected)!=null)
        {
            this.selectedSpot = myBoard.getSpot(selected);
        }
        if (selectedSpot.getPiece()!=null) {
            List<Coordinate> possibleMovePositions = selectedSpot.getPiece().getPossibleMoves().getPossibleSpots(selected);
            List<Coordinate> possibleCapturePositions = selectedSpot.getPiece().getPossibleCaptures().getPossibleSpots(selected);


            Boolean isJump = selectedSpot.getPiece().getCanJump();

            if (isJump) possibleCoordinates = getJumpPossibleCoordinate(possibleMovePositions);
            else possibleCoordinates = getStandardPossibleCoordinate(possibleMovePositions);

            if (possibleCapturePositions.size() > 0) addCapturePositions(possibleCapturePositions);
        }
    }

    public Set<Spot> getPossibleCoordinates(GameCoordinate selected){
        searchPossiblePositions(selected);

        Set<Spot> possibleSet = new HashSet<>();

        for(int i = 0; i < possibleCoordinates.size(); i++){
            possibleSet.add(myBoard.getSpot(possibleCoordinates.get(i)));
        }

        return possibleSet;
    }

    protected void makeBoard(GameBoard newBoard){
        this.myBoard = newBoard;
    }

    private Piece getMovingPiece(GameCoordinate piecePosition, List<Spot> board){
        Piece movingPiece = null;
        for(int i = 0; i < board.size(); i++){
            Spot spot = board.get(i);
            if(spot.getCoordinate() == piecePosition){
                movingPiece = spot.getPiece();
                spot.setPiece(null);
                break;
            }
        }
        return movingPiece;
    }

    private void removePieceFromGame(Piece capturedPiece){
        if(capturedPiece.getCheckable()) isGameOver = true;
    }

    private List<Spot> setMovingPiece(GameCoordinate newPosition, List<Spot> board, Piece movingPiece){
        for(int i = 0; i < board.size(); i++){
            Spot spot = board.get(i);
            if(spot.getCoordinate() == newPosition){
                if(spot.isEmpty()) spot.setPiece(movingPiece);
                else {
                    removePieceFromGame(spot.getPiece());
                    spot.setPiece(movingPiece);
                }
                break;
            }
        }
        return board;
    }

    public List<Spot> movePiece(GameCoordinate prevPosition, GameCoordinate newPosition){
        List<Spot> board = myBoard.getFullBoard();

        Piece movingPiece = getMovingPiece(prevPosition, board);

        return setMovingPiece(newPosition, board, movingPiece);
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public Spot getSpot(GameCoordinate coordinate){
        return myBoard.getSpot(coordinate);
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
