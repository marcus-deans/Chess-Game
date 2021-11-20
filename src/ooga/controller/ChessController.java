package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.game.Game;
import ooga.view.GameView;
import ooga.view.View;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * The chess controller will handle taking in data using the file parsers and pass the information
 * to the model, as well as handling changed between the front end and the backend
 * @author Carter Stonesifer
 *
 */
public class ChessController implements Controller {
    private GameView myGameView;

    private int BOARDWIDTH;
    private int BOARDHEIGHT;
    private GameBoard myBoard;


    private GameBoard initialBoard;
    private Map<String, String> myData;
    private CSVParser myCSVParser = new CSVParser();
    private SIMParser mySIMParser = new SIMParser();

    private Game myGame;

    private boolean FIRSTCLICK;
    private GameCoordinate clickedPiece;
    private GameCoordinate nextMove;

    //TODO: Replace with player class and login
    private int numTurns;
    private String[] players = {"A", "B"};
    private String currentPlayer;

    public ChessController(int width, int height, String background, String filename){
        myGameView = new GameView(width, height, background, filename, this);
        myGameView.start(new Stage());
    }

    public ChessController(int width, int height, String background){
        new ChessController(width, height, background, "Standard.sim");
    }

    @Override
    public void initializeFromFile(File file) throws CsvValidationException, IOException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        File simFile= new File(file.toString());
        myData = mySIMParser.readSimFile(simFile);
        File csvFile= new File("data/Standard.csv");
        myCSVParser.readCSVFile(csvFile);

        BOARDWIDTH = myCSVParser.getDimensions()[0];
        BOARDHEIGHT = myCSVParser.getDimensions()[1];
        initialBoard.setupBoard(myCSVParser.getInitialStates());
        myBoard = initialBoard;
        myGame =  new Game(myBoard,  myData);
        FIRSTCLICK = true;
        numTurns = 2;
    }

    @Override
    public void setDisplay(View view) {

    }

    @Override
    public Stage getStage() {
        return null;
    }

    @Override
    public Game getCurrentGame() {
        return myGame;
    }

    @Override
    public void resetGame() {
        myBoard = initialBoard;
    }

    @Override
    public void setTime(int speed) {

    }
    @Override
    public void clickState(int row, int column){
        if (FIRSTCLICK){
            handleFirstClick(row, column);
        }
        else {
            handleSecondClick(row, column);
        }
    }

    private void handleFirstClick(int row, int column) {
        clickedPiece = new GameCoordinate(row, column);
        //TODO: if piece belongs to player
        myGame.update(clickedPiece);
        GameView.highlightCellOptions(myGame.getPossibleCoordinates());
        FIRSTCLICK = false;
    }

    private void handleSecondClick(int row, int column) {
        nextMove = new GameCoordinate(row, column);
        if(nextMove==clickedPiece){
            FIRSTCLICK = true;
            clickState(row, column);
        }
        //TODO: if piece belongs to player (currentPlayer)
        if (myGame.getPossibleCoordinates().contains(nextMove)){
            //update the board with clicked piece at nextMove Coordinate: Works?
            clickedPiece.setCoordinate(nextMove);
            //myGameView.updateChessCell(/*A spot goes in here*/);
            //updates board
            nextTurn();
        }
        else{
            //do nothing
        }


    }

    private void nextTurn(){
        numTurns++;
        currentPlayer = players[numTurns%2];
    }
}
