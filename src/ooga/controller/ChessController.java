package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.board.GameBoard;
import ooga.logic.game.Game;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import ooga.view.GameView;
import ooga.view.View;

import java.io.File;
import java.io.IOException;
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
    private String[][] myBoard;
    private String[][] initialBoard;
    private Map<String, String> myData;
    private CSVParser myCSVParser = new CSVParser();
    private SIMParser mySIMParser = new SIMParser();

    private Game myGame;
    private GameBoard myGameBoard;

    public ChessController(int width, int height, String background, String filename){
        myGameView = new GameView(width, height, background, filename, this);
        //TODO: Uncomment the next line when functional
//        myGameView.start(new Stage());
    }

//    public ChessController(int width, int height, String background){
//        new ChessController(width, height, background, "Standard.sim");
//    }


    void setData(File file) throws CsvValidationException, IOException, IncorrectCSVFormatException, IncorrectSimFormatException {
        File simFile= new File(String.valueOf(file)); //TODO: Set up choosing files
        myData = mySIMParser.readSimFile(simFile);
        File csvFile= new File(myData.get("GameConfiguration"));
        myCSVParser.readCSVFile(csvFile);
        BOARDWIDTH = myCSVParser.getDimensions()[0];
        BOARDHEIGHT = myCSVParser.getDimensions()[1];
        myBoard = myCSVParser.getInitialStates();
    }
    public int getHeight(){
        return BOARDHEIGHT;
    }

    private void initializeGame(){

    }


    @Override
    public void initializeFromFile(File file) {
    }

    private void selectPiece(int i, int j){

    }

    @Override
    public void setDisplay(View view) {

    }

    @Override
    public Stage getStage() {
        return null;
    }

    @Override
    public void resetGame() {
        myBoard = initialBoard;
    }

    @Override
    public void setTime(int speed) {

    }

    @Override
    public void undoMove() {

    }

    @Override
    public void redoMove() {

    }

    @Override
    public void changeVariant(String variant) {

    }

}
