package ooga.logic.game;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private GameBoard myBoard;
    private Coordinate selected;
    private Player currentPlayer;
    private GameSpot selectedSpot;
    private List<Coordinate> possibleCoordinates;
    private boolean isGameOver;
    private Logger myLogger;
    private Coordinate puzzleStart;
    private Coordinate puzzleFinish;
    private String gameType;
    private boolean isAtomic;

    public Game(int height, int width, Map<String,String> myMap){
        myBoard = new GameBoard(height, width, myMap);
        myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        isGameOver = false;
        isAtomic = false;
    }

    public void setEdgePolicy(String s){
        try{
            myBoard.setEdgePolicy(s);
        }
        catch (Exception e){
            //myBoard.setEdgePolicy("Basic");
        }
    }


    public List<Spot> getFullBoard(){
        return myBoard.getFullBoard();
    }

    public void setupBoard(String spot, int i, int j){
        try {

            myBoard.setupBoard(spot, i, j);
        } catch (Exception e){

        }

    }

    public void reset(){
        myBoard.reset();
    }

    public void setSelected(Coordinate select){
        selected = select;
    }
    
    private List<Coordinate> filterCaptures(Coordinate selected) {
        Piece myPiece = getMyPiece(selected);
        List<List<Coordinate>> possibleCapturePositions = myPiece.getPossibleCaptures().getPossibleSpots(selected);
        possibleCapturePositions = applyEdgePolicy(possibleCapturePositions);

        List<Coordinate> listToPopulate = new ArrayList<>();
        for (List<Coordinate> eachLine: possibleCapturePositions) {
            for (Coordinate individualCoord : eachLine) {
                Piece tempPiece = myBoard.getSpot(individualCoord).getPiece();
                // not an empty square
                if (tempPiece == null){
                    continue;
                }


                //Opponent: Eat and stop
                if (getTeam(tempPiece) != getTeam(myPiece)){
                    listToPopulate.add(individualCoord);
                    break;
                }

                //can cannibalize
                if (myPiece.canCannibalize()){
                    listToPopulate.add(individualCoord);
                    // can jump; continue, else break:
                    if (myPiece.getCanJump()){
                        continue;
                    }
                    break;
                }

                // me and can jump
                if (getCanJump(myPiece)){
                    continue;
                }
                break;
            }
        }

        return listToPopulate;
    }

    private boolean getCanJump(Piece piece) {
        return piece.getCanJump();
    }

    private int getTeam(Piece piece) {
        return piece.getTeam();
    }


    private List<Coordinate> filterMoves(Coordinate selected) {
        Piece myPiece = getMyPiece(selected);
        List<List<Coordinate>> possibleMovePositions = myPiece.getPossibleMoves().getPossibleSpots(selected);
        possibleMovePositions = applyEdgePolicy(possibleMovePositions);

        List<Coordinate> listToPopulate = new ArrayList<>();
        for (List<Coordinate> eachLine: possibleMovePositions) {
            for (Coordinate individualCoord : eachLine) {
                Piece tempPiece = myBoard.getSpot(individualCoord).getPiece();

                // empty piece: CAN MOVE HERE
                if (tempPiece == null){
                    listToPopulate.add(individualCoord);
                    continue;
                }

                // can't jump? break
                if (!getCanJump(myPiece)){
                    break;
                }

                // can jump and different piece
                if (getTeam(tempPiece) == getTeam(myPiece)){
                    continue;
                }
                break;
            }
        }
        return listToPopulate;


        }

    private List<List<Coordinate>> applyEdgePolicy(List<List<Coordinate>> possiblePositions) {
        return myBoard.getEdgePolicy().filterList(possiblePositions);
    }

    private Piece getMyPiece(Coordinate selected) {
        return myBoard.getSpot(selected).getPiece();
    }

    public Set<Spot> getPossibleCoordinates(Coordinate selected, int team){
        List<Coordinate> myMoveList = filterMoves(selected);
        List<Coordinate> myCaptureList = filterCaptures(selected);
        myMoveList.addAll(myCaptureList);

        Set<Spot> possibleSet = myMoveList.stream().
            map(myBoard::getSpot).collect(Collectors.toSet());
        return possibleSet;
    }

    private void removePieceFromGame(Piece capturedPiece){

        try {
            if (capturedPiece.getCheckable()) isGameOver = true;
            if (isAtomic) isGameOver=checkAtomic(capturedPiece);
            currentPlayer.addPieceToGraveyard(capturedPiece);
        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in removePiece");
        }
    }

    private boolean checkAtomic(Piece capturedPiece)
    {
        List<List<Coordinate>> list=capturedPiece.getAtomicArea().getPossibleSpots(capturedPiece.getCoordinate());
        for (int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size(); j++){
                if(getSpot(list.get(i).get(j)).getPiece().getCheckable())
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void setMovingPiece(Coordinate newPosition, Piece movingPiece)
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

    public void movePiece(Coordinate prevPosition, Coordinate newPosition)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            setMovingPiece(newPosition, myBoard.getSpot(prevPosition).getPiece());
            myBoard.getSpot(prevPosition).setPiece(null);
            if(gameType.equals("Puzzles"))
            {
                isGameOver=puzzleRules(prevPosition,newPosition);
            }
            if(gameType.equals("Atomic"))
            {
                myBoard.setAtomic(true);
            }
        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in movePiece");
        }
    }

    public Boolean puzzleRules(Coordinate prevPosition, Coordinate newPosition)
    {
        boolean gameOver=false;
        if (puzzleStart!=null && puzzleFinish!=null && puzzleStart.equals(prevPosition) && puzzleFinish.equals(newPosition))
        {
            myLogger.log(Level.INFO, "PUZZLE COMPLETED SUCCESSFULLY");
            gameOver = true;
        }
        else if (puzzleStart!=null && puzzleFinish!=null && (!puzzleStart.equals(prevPosition) || !puzzleFinish.equals(newPosition)))
        {
            myLogger.log(Level.INFO, "PUZZLE FAILED! TRY AGAIN!");
            reset();
        }
        return gameOver;
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }


    public void resetClick(){

    }

    public Spot getSpot(Coordinate coordinate){
        return myBoard.getSpot(coordinate);
    }

    public void setPuzzleSolution(String s)
    {
        puzzleStart = new GameCoordinate(Integer.parseInt(s.substring(0,1)),Integer.parseInt(s.substring(1,2)));
        puzzleFinish = new GameCoordinate(Integer.parseInt(s.substring(2,3)),Integer.parseInt(s.substring(3,4)));
    }


    protected void consumerGenerateNextState(int currentState, Consumer<Integer> consumer) {
        try {
            consumer.accept(currentState);
        } catch (NullPointerException e) {
            //myErrorFactory.updateError(GAME_ERROR);
        }
    }

    public void setGameType(String type)
    {
        this.gameType=type;
        if (gameType.equals("Atomic")) isAtomic=true;
    }

    public void pawnsToPiece(String piece) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myBoard.pawnsToPiece(piece);
    }

    public void makePiecesJump()
    {
        myBoard.makePiecesJump();
    }

    public void makePiecesCannibalize()
    {
        myBoard.makePiecesCannibalize();
    }
}
