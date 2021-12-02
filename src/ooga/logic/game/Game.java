package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.board.spot.spotactions.SpotAction;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Game {
    private GameBoard myBoard;
    private List<Spot> initialBoard;

    //A map containing the game's data collected from the game's sim files.
    private Map<String, String> metadata;
    private GameCoordinate selected;
    private Player currentPlayer;
    private GameSpot selectedSpot;
    private List<Coordinate> possibleCoordinates;
    private boolean isGameOver;

    public Game(int height, int width){
        myBoard = new GameBoard(height, width);
    }

    public void setEdgePolicy(String s){
        try{
            myBoard.setEdgePolicy(s);
        }
        catch (Exception e){

        }

    }

    public List<Spot> getFullBoard(){
        return myBoard.getFullBoard();
    }

    public void setupBoard(String spot, int i, int j){
        try {
            myBoard.setupBoard(spot, i, j);
            initialBoard = myBoard.getFullBoard();
        } catch (Exception e){

        }

    }

    public void reset(){
        myBoard.reset(initialBoard);
    }

    public void setSelected(GameCoordinate select){
        selected = select;
    }

    public int getSelectedTeam(){
        return myBoard.getSpot(selected).getPiece().getTeam();
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

        List<Coordinate> possibleMovePositions = myBoard.getSpot(selected).getPiece().getPossibleMoves().getPossibleSpots(selected);
        //searchPossiblePositions(selected);

        Set<Spot> possibleSet = new HashSet<>();

//        for(int i = 0; i < possibleCoordinates.size(); i++){
//            possibleSet.add(myBoard.getSpot(possibleCoordinates.get(i)));
//        }

        for(int i = 0; i < possibleMovePositions.size(); i++){
            possibleSet.add(myBoard.getSpot(possibleMovePositions.get(i)));
        }

        return possibleSet;
    }

    private void removePieceFromGame(Piece capturedPiece){
        if(capturedPiece.getCheckable()) isGameOver = true;
        currentPlayer.addPieceToGraveyard(capturedPiece);
    }

    private void setMovingPiece(GameCoordinate newPosition, Piece movingPiece)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        if(myBoard.hasPiece(newPosition)) removePieceFromGame(myBoard.getSpot(newPosition).getPiece());
        myBoard.updateBoard(newPosition,movingPiece);
    }

    public void movePiece(GameCoordinate prevPosition, GameCoordinate newPosition)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       myBoard.getSpot(prevPosition).setPiece(null);
        setMovingPiece(newPosition, myBoard.getSpot(prevPosition).getPiece());
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public Spot getSpot(GameCoordinate coordinate){
        return myBoard.getSpot(coordinate);
    }

    public void resetClick(){

    }

    public Spot getSpot(Coordinate coordinate){
        return myBoard.getSpot(coordinate);
    }
}
