package ooga.logic.game;

import java.util.function.Consumer;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.board.spot.spotactions.SpotAction;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Coordinate puzzleStart;
    private Coordinate puzzleFinish;
    private int currentTeam;

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

    private List<Coordinate> getJumpPossibleCoordinate(List<List<Coordinate>> list){
        List<Coordinate> myNewList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size(); j++){
                if(!myBoard.hasPiece(list.get(i).get(j))){
                   myNewList.add(list.get(i).get(j));
                }
            }

        }
        return myNewList;
    }


    private List<Coordinate> getStandardPossibleCoordinate(List<List<Coordinate>> list){
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (List<Coordinate> miniList : list) {
            for (int i = 0; i < list.size(); i++) {
                if (myBoard.hasPiece(miniList.get(i))) {
                    int posX = miniList.get(i).getX_pos();
                    int posY = miniList.get(i).getY_pos();
                    //maxX = (selected.getX_pos() < posX && posX < maxX) ? posX: maxX;

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
        }

        List<Coordinate> possiblePositions = new ArrayList<>();

        int finalMaxY = maxY;
        int finalMinY = minY;
        int finalMaxX = maxX;
        int finalMinX = minX;
        for (List<Coordinate> myIndividualList : list){
            List<Coordinate> myListToAdd = new ArrayList<>();
            myIndividualList.stream().
                filter(elem -> (elem.getY_pos() < finalMaxY) && (elem.getY_pos() > finalMinY)).
                filter(elem -> (elem.getY_pos() < finalMaxX) && (elem.getY_pos() > finalMinX)).
                forEach(elem-> possiblePositions.add(elem));
        }


        return possiblePositions;
    }

    private void addCapturePositions(List<List<Coordinate>> possibleCapture){
        for (List<Coordinate> miniCaptureSet : possibleCapture){
            miniCaptureSet.stream().filter(piece -> myBoard.hasPiece(piece)).
                forEach(piece -> possibleCoordinates.add(piece));
        }
//        for(Coordinate current : possibleCapture){
//            if(myBoard.hasPiece(current)) possibleCoordinates.add(current);
//            System.out.println(current.getX_pos() + " " + current.getY_pos());
//        }
    }

    public void searchPossiblePositions(GameCoordinate selected){
        if (myBoard.getSpot(selected)!=null)
        {
            this.selectedSpot = myBoard.getSpot(selected);
        }
        if (selectedSpot.getPiece()!=null) {

            List<List<Coordinate>> possibleMovePositions = selectedSpot.getPiece().getPossibleMoves().getPossibleSpots(selected);
            List<List<Coordinate>> possibleCapturePositions = selectedSpot.getPiece().getPossibleCaptures().getPossibleSpots(selected);


            Boolean isJump = selectedSpot.getPiece().getCanJump();

//            if (isJump) possibleCoordinates = getJumpPossibleCoordinate(possibleMovePositions);
//            else possibleCoordinates = getStandardPossibleCoordinate(possibleMovePositions);
            possibleCoordinates = (isJump) ? getJumpPossibleCoordinate(possibleMovePositions) :
                getStandardPossibleCoordinate(possibleMovePositions);


            if (possibleCapturePositions.size() > 0) addCapturePositions(possibleCapturePositions);
        }
    }

    public Set<Spot> getPossibleCoordinates(GameCoordinate selected, int team){
        currentTeam = team;
        List<List<Coordinate>> possibleMovePositions = myBoard.getSpot(selected).getPiece().getPossibleMoves().getPossibleSpots(selected);

        possibleMovePositions = myBoard.getEdgePolicy().filterList(possibleMovePositions);

        Set<Coordinate> blackList = new HashSet<>();
        Set<Coordinate> actualList = new HashSet<>();
        for (List<Coordinate> eachLine: possibleMovePositions) {
            for (Coordinate individualCoord : eachLine) {
                Piece tempPiece = myBoard.getSpot(individualCoord).getPiece();

                if (tempPiece != null && !tempPiece.getCanJump()) {
                    int xDif = selected.getX_pos() - individualCoord.getX_pos();
                    int yDif = selected.getY_pos() - individualCoord.getY_pos();

                    if (tempPiece.getTeam() == currentTeam)
                        blackList.add(individualCoord);
                    for (Coordinate eachCoordinate : eachLine) {
                        if (xDif > 0 && yDif == 0
                            && selected.getY_pos() == eachCoordinate.getY_pos() &&
                            selected.getX_pos() - eachCoordinate.getX_pos() > xDif) {
                            blackList.add(eachCoordinate);
                        } else if (xDif < 0 && yDif == 0
                            && selected.getY_pos() == eachCoordinate.getY_pos() &&
                            selected.getX_pos() - eachCoordinate.getX_pos() < xDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif > 0 && xDif == 0
                            && selected.getX_pos() == eachCoordinate.getX_pos() &&
                            selected.getY_pos() - eachCoordinate.getY_pos() > yDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif < 0 && xDif == 0
                            && selected.getX_pos() == eachCoordinate.getX_pos() &&
                            selected.getY_pos() - eachCoordinate.getY_pos() < yDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif > 0 && xDif > 0
                            && selected.getY_pos() - eachCoordinate.getY_pos() > yDif
                            && selected.getX_pos() - eachCoordinate.getX_pos() > xDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif > 0 && xDif < 0
                            && selected.getY_pos() - eachCoordinate.getY_pos() > yDif
                            && selected.getX_pos() - eachCoordinate.getX_pos() < xDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif < 0 && xDif > 0
                            && selected.getY_pos() - eachCoordinate.getY_pos() < yDif
                            && selected.getX_pos() - eachCoordinate.getX_pos() > xDif) {
                            blackList.add(eachCoordinate);
                        } else if (yDif < 0 && xDif < 0
                            && selected.getY_pos() - eachCoordinate.getY_pos() < yDif
                            && selected.getX_pos() - eachCoordinate.getX_pos() < xDif) {
                            blackList.add(eachCoordinate);
                        }
                    }
                }
            }
        }

        //possibleMovePositions.stream().forEach(piece -> possibleSet.add(myBoard.getSpot(piece)));

        Set<Spot> possibleSet = new HashSet<>();
        for(List<Coordinate> myMiniList : possibleMovePositions){
            for(Coordinate individualCoord : myMiniList){
                if(!blackList.contains(individualCoord)){
                    possibleSet.add(myBoard.getSpot(individualCoord));
                }
            }
        }

        


        return possibleSet;
    }

    private void removePieceFromGame(Piece capturedPiece){

        try {
            if (capturedPiece.getCheckable()) isGameOver = true;
            currentPlayer.addPieceToGraveyard(capturedPiece);
        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in removePiece");
        }
    }

    private void setMovingPiece(GameCoordinate newPosition, Piece movingPiece)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {


        try {
            if(myBoard.hasPiece(newPosition)) removePieceFromGame(myBoard.getSpot(newPosition).getPiece());
            myBoard.updateBoard(newPosition,movingPiece);
        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in setMovingPiece");
        }
    }

    public void movePiece(GameCoordinate prevPosition, GameCoordinate newPosition)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            setMovingPiece(newPosition, myBoard.getSpot(prevPosition).getPiece());
            myBoard.getSpot(prevPosition).setPiece(null);
            if (puzzleStart!=null && puzzleFinish!=null && puzzleStart.equals(prevPosition) && puzzleFinish.equals(newPosition))
            {
                myLogger.log(Level.INFO, "PUZZLE COMPLETED SUCCESSFULLY");
                isGameOver = true;
            }
            else if (puzzleStart!=null && puzzleFinish!=null && (!puzzleStart.equals(prevPosition) || !puzzleFinish.equals(newPosition)))
            {
                myLogger.log(Level.INFO, "PUZZLE FAILED! TRY AGAIN!");
                reset();
            }



        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in movePiece");
        }
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

    public void setPuzzleSolution(String s)
    {
        puzzleStart=new GameCoordinate(Integer.parseInt(s.substring(0,1)),Integer.parseInt(s.substring(1,2)));
        puzzleFinish=new GameCoordinate(Integer.parseInt(s.substring(2,3)),Integer.parseInt(s.substring(3,4)));
    }


    protected void consumerGenerateNextState(int currentState, Consumer<Integer> consumer) {
        try {
            consumer.accept(currentState);
        } catch (NullPointerException e) {
            //myErrorFactory.updateError(GAME_ERROR);
        }
    }
}
