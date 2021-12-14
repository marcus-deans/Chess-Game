package ooga.logic.game;

import java.util.stream.Collectors;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.Spot;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private GameBoard myBoard;
    private Coordinate selected;
    private Player currentPlayer;
    private boolean isGameOver;
    private Logger myLogger;
    private Coordinate puzzleStart;
    private Coordinate puzzleFinish;
    private String gameType;
    private boolean isAtomic;
    private boolean filter;

    /**
     * Game constructor. Height and width and myMap are taken in as parameters which are then used to make GameBoard
     * using those values.
     *
     * @param height
     * @param width
     * @param myMap
     */
    public Game(int height, int width, Map<String,String> myMap){
        myBoard = new GameBoard(height, width, myMap);
        myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        isGameOver = false;
        isAtomic = false;
        filter = true;
    }

    /**
     * Takes in the corresponding edge policy as read from the parser and sets it in this class so that
     * the getting possible moves corresponds with the selected policy.
     *
     * @param policy
     */
    public void setEdgePolicy(String policy){
        try{
            myBoard.setEdgePolicy(policy);
        }
        catch (Exception e){
        }
    }

    /**
     * returns List<Spot> from myBoard
     *
     * @return myBoard.getFullBoard();
     */
    public List<Spot> getFullBoard(){
        return myBoard.getFullBoard();
    }

    /**
     * sets up each spot on the board.
     *
     * @param spot
     * @param i pos
     * @param j pos
     */
    public void setupBoard(String spot, int i, int j){
        try {
            myBoard.setupBoard(spot, i, j);
        } catch (Exception e){

        }

    }

    /**
     * resets the board
     */
    public void reset(){
        myBoard.reset();
    }

    /**
     * sets selected spot
     * @param select
     */
    public void setSelected(Coordinate select){
        selected = select;
    }

    /**
     * filters possible capture positions based on where other pieces are placed on the board
     * relative to the selected piece (that is about to move)
     * @param selected
     */
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

    /**
     * get whether a piece has the ability to jump
     * @param piece
     */
    private boolean getCanJump(Piece piece) {
        return piece.getCanJump();
    }

    /**
     * get the team of the piece
     * @param piece
     */
    private int getTeam(Piece piece) {
        return piece.getTeam();
    }


    /**
     * Filter the move of the selected piece that the user can move. returns a list of available coordinates
     *
     * @param selected
     */
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

    /**
     * sets the correct edge policy
     * @param possiblePositions
     */
    private List<List<Coordinate>> applyEdgePolicy(List<List<Coordinate>> possiblePositions) {
        return myBoard.getEdgePolicy().filterList(possiblePositions);
    }

    private Piece getMyPiece(Coordinate selected) {
        return myBoard.getSpot(selected).getPiece();
    }

    /**
     * API for getting possible moves for the selected piece
     *
     * @param selected
     */
    public Set<Spot> getPossibleCoordinates(Coordinate selected, int team){
        List<Coordinate> myMoveList = filterMoves(selected);
        List<Coordinate> myCaptureList = filterCaptures(selected);
        myMoveList.addAll(myCaptureList);
        Set<Spot> possibleSet = myMoveList.stream().
            map(myBoard::getSpot).collect(Collectors.toSet());
        return possibleSet;
    }

    /**
     * removes piece from the game if it has been captured. Also records whether a checkable piece
     * has been captured and ends game accordingly
     *
     * @param capturedPiece
     */
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

    /**
     * checks if capturedpiece is atomic
     *
     * @param capturedPiece
     */
    private boolean checkAtomic(Piece capturedPiece)
    {
        List<List<Coordinate>> list = capturedPiece.getAtomicArea().getPossibleSpots(capturedPiece.getCoordinate());
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

    /**
     * moves the moving piece to the new location
     *
     * @param newPosition
     * @param movingPiece
     */
    private void setMovingPiece(Coordinate newPosition, Piece movingPiece) {
        try {
            if(myBoard.hasPiece(newPosition)) removePieceFromGame(myBoard.getSpot(newPosition).getPiece());
            myBoard.updateBoard(newPosition,movingPiece);
        }
        catch(Exception e)
        {
            myLogger.log(Level.INFO, "Error in setMovingPiece");
        }
    }

    /**
     * API for moving a piece on the board.
     *
     * @param prevPosition
     * @param newPosition
     */
    public void movePiece(Coordinate prevPosition, Coordinate newPosition){
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

    /**
     * sets puzzle rules
     *
     * @param prevPosition
     * @param newPosition
     */
    public Boolean puzzleRules(Coordinate prevPosition, Coordinate newPosition)
    {
        boolean gameOver=false;
        if (puzzleStart!=null && puzzleFinish!=null && puzzleStart.equals(prevPosition) && puzzleFinish.equals(newPosition)) {
            myLogger.log(Level.INFO, "PUZZLE COMPLETED SUCCESSFULLY");
            gameOver = true;
        }
        else if (puzzleStart!=null && puzzleFinish!=null && (!puzzleStart.equals(prevPosition) || !puzzleFinish.equals(newPosition))) {
            myLogger.log(Level.INFO, "PUZZLE FAILED! TRY AGAIN!");
            reset();
        }
        return gameOver;
    }

    /**
     * returns whether the game is over or not
     *
     */
    public boolean getIsGameOver(){
        return isGameOver;
    }

    public void setIsGameOver(boolean newGameOver){
        isGameOver= newGameOver;
    }

    /**
     * returns spot given coordinate
     *
     * @param coordinate
     */
    public Spot getSpot(Coordinate coordinate){
        return myBoard.getSpot(coordinate);
    }


    public void setPuzzleSolution(String s)
    {
        puzzleStart = new GameCoordinate(Integer.parseInt(s.substring(0,1)),Integer.parseInt(s.substring(1,2)));
        puzzleFinish = new GameCoordinate(Integer.parseInt(s.substring(2,3)),Integer.parseInt(s.substring(3,4)));
    }

    /**
     * sets the type of the game
     *
     * @param type
     */
    public void setGameType(String type)
    {
        this.gameType=type;
        if (gameType.equals("Atomic")) isAtomic=true;
    }

    /**
     * turns all piece to pawns - cheat code
     *
     * @param piece
     */
    public void pawnsToPiece(String piece) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myBoard.pawnsToPiece(piece);
    }

    /**
     * makes all pieces jump - cheat code
     */
    public void makePiecesJump()
    {
        myBoard.makePiecesJump();
    }

    /**
     * makes all pieces cannibalized - cheat code
     */
    public void makePiecesCannibalize()
    {
        myBoard.makePiecesCannibalize();
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

}
