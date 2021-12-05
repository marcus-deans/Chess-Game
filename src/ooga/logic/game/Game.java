package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.board.spot.spotactions.SpotAction;

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

    public Game(int height, int width) throws IOException {
        myBoard = new GameBoard(height, width);

        URL url = new URL("http://localhost:3001/createUser?id=test2&password=test");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        System.out.println(result.toString());
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

    public Set<Spot> getPossibleCoordinates(GameCoordinate selected, int team){
        currentTeam = team;
        List<Coordinate> possibleMovePositions = myBoard.getSpot(selected).getPiece().getPossibleMoves().getPossibleSpots(selected);

        possibleMovePositions = myBoard.getEdgePolicy().filterList(possibleMovePositions);

        Set<Coordinate> blackList = new HashSet<>();
        for(int i = 0; i < possibleMovePositions.size(); i++){
            Piece tempPiece = myBoard.getSpot(possibleMovePositions.get(i)).getPiece();

            if(tempPiece != null && !tempPiece.getCanJump()){
                int xDif = selected.getX_pos() - possibleMovePositions.get(i).getX_pos();
                int yDif = selected.getY_pos() - possibleMovePositions.get(i).getY_pos();

                if(tempPiece.getTeam() == currentTeam) blackList.add(possibleMovePositions.get(i));
                for(int j = 0; j < possibleMovePositions.size(); j++){
                    if(xDif > 0 && yDif == 0 && selected.getY_pos() == possibleMovePositions.get(j).getY_pos() &&
                            selected.getX_pos() - possibleMovePositions.get(j).getX_pos() > xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(xDif < 0 && yDif == 0 && selected.getY_pos() == possibleMovePositions.get(j).getY_pos() &&
                            selected.getX_pos() - possibleMovePositions.get(j).getX_pos() < xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif > 0 && xDif == 0 && selected.getX_pos() == possibleMovePositions.get(j).getX_pos() &&
                            selected.getY_pos() - possibleMovePositions.get(j).getY_pos() > yDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif < 0 && xDif == 0 && selected.getX_pos() == possibleMovePositions.get(j).getX_pos() &&
                            selected.getY_pos() - possibleMovePositions.get(j).getY_pos() < yDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif > 0 && xDif > 0 && selected.getY_pos() - possibleMovePositions.get(j).getY_pos() > yDif
                            && selected.getX_pos() - possibleMovePositions.get(j).getX_pos() > xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif > 0 && xDif < 0 && selected.getY_pos() - possibleMovePositions.get(j).getY_pos() > yDif
                            && selected.getX_pos() - possibleMovePositions.get(j).getX_pos() < xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif < 0 && xDif > 0 && selected.getY_pos() - possibleMovePositions.get(j).getY_pos() < yDif
                            && selected.getX_pos() - possibleMovePositions.get(j).getX_pos() > xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                    else if(yDif < 0 && xDif < 0 && selected.getY_pos() - possibleMovePositions.get(j).getY_pos() < yDif
                            && selected.getX_pos() - possibleMovePositions.get(j).getX_pos() < xDif){
                        blackList.add(possibleMovePositions.get(j));
                    }
                }
            }
        }
        
        Set<Spot> possibleSet = new HashSet<>();
        for(int i = 0; i < possibleMovePositions.size(); i++){
            if(!blackList.contains(possibleMovePositions.get(i))){
                possibleSet.add(myBoard.getSpot(possibleMovePositions.get(i)));
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
}
