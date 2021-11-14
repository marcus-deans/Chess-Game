package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.board.GameBoard;
import ooga.logic.game.Game;
import ooga.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class ChessController implements Controller {

    private int BOARDWIDTH;
    private int BOARDHEIGHT;
    private String[][] myBoard;
    private String[][] initialBoard;
    private Map<String, String> myData;
    private CSVParser myCSVParser = new CSVParser();
    private SIMParser mySIMParser = new SIMParser();

    private Game myGame;
    private GameBoard myGameBoard;


    private void setData() throws CsvValidationException, IOException {
        File simfile= new File("data/Standard.sim"); //TODO: Set up choosing files
        File csvfile= new File("data/Standard.csv");
        myCSVParser.readCSVFile(csvfile);

        myData = mySIMParser.readSimFile(simfile);

        BOARDWIDTH = myCSVParser.getDimensions()[0];
        BOARDHEIGHT = myCSVParser.getDimensions()[1];
        myBoard = myCSVParser.getInitialStates();
    }


    @Override
    public void initializeFromFile(File file) {




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
        return null;
    }

    @Override
    public void resetGame() {
        myBoard = initialBoard;
    }

    @Override
    public void setTime(int speed) {

    }

}
