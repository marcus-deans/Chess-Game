package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.game.Game;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import ooga.view.GameView;
import ooga.view.View;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private Stack<GameCoordinate[]> history;


    private GameBoard initialBoard;
    private Map<String, String> myData;
    private CSVParser myCSVParser = new CSVParser();
    private SIMParser mySIMParser = new SIMParser();

    private Game myGame;

    private boolean FIRSTCLICK = true;
    private GameCoordinate clickedPiece;
    private GameCoordinate nextMove;

    //TODO: Replace with player class and login
    private int numTurns;
    private String[] players = {"Player 1", "Player 2"};
    private String currentPlayer;

    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Instantiates the chess controller and allow the game to be initiated.
     *
     * @param width
     * @param height
     * @param background
     * @param filename
     */
    public ChessController(int width, int height, String background, String filename) {
        myGameView = new GameView(width, height, background, filename, this);
        myGame = new Game(myBoard, myData);
        myGameView.start(new Stage());
    }

    /**
     * Takes in the information from the SIM FILE, thus the CSV as well, and instantiates backend classes
     * with the required data
     *
     * @param file
     * @throws CsvValidationException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IncorrectCSVFormatException
     */
    @Override
    public void initializeFromFile(File file) throws CsvValidationException, IOException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException {

        File simFile = new File(file.toString());
        myData = mySIMParser.readSimFile(simFile);
        File csvFile = new File(myData.get("GameConfiguration"));
        myCSVParser.readCSVFile(csvFile);
        BOARDWIDTH = myCSVParser.getDimensions()[0];
        BOARDHEIGHT = myCSVParser.getDimensions()[1];
        initialBoard = new GameBoard(BOARDHEIGHT, BOARDWIDTH);
        myBoard = new GameBoard(BOARDHEIGHT, BOARDWIDTH);

        boardInitializer(myCSVParser.getInitialStates(), initialBoard);


        myBoard = initialBoard;
        myGame = new Game(myBoard, myData);
        numTurns = 0;
    }

    public void boardInitializer(String[][] initialStates, GameBoard board){
        String twoDBoard[][] = initialStates;
        for(String row[]: twoDBoard){
            for(String i: row){
                //board./*TODO: call board setup method for each string*/(i);
            }
        }
    }

    /**
     * Gives BOARDHEIGHT
     * @return
     */
    public int getHeight() {
        return BOARDHEIGHT;
    }

    /**
     * Gives BOARDWIDTH
     * @return
     */
    public int getWidth() {
        return BOARDWIDTH;
    }

    @Override
    public void resetGame() {
        myBoard = initialBoard;
    }

    @Override
    public void setTime(int speed) {

    }

    @Override
    public void clickedCoordinates(int row, int column) {
        if (FIRSTCLICK) {
            handleFirstClick(row, column);
        } else {
            handleSecondClick(row, column);
        }
    }

    private void handleFirstClick(int row, int column) {
        clickedPiece = new GameCoordinate(row, column);
        myGame.setSelected(clickedPiece);
        //TODO: if piece belongs to player (currentPlayer)
        myGame.searchPossiblePositions(clickedPiece);
        myGameView.highlightCellOptions(myGame.getPossibleCoordinates(clickedPiece));
        myLogger.log(Level.INFO, "FIRST CLICK");
        FIRSTCLICK = false;
    }

        private void handleSecondClick ( int row, int column){
            nextMove = new GameCoordinate(row, column);
            //clicking same piece to deselect
            if (nextMove.equals(clickedPiece)) {
                myLogger.log(Level.INFO, "SAME PIECE");
                FIRSTCLICK = true;
                myGameView.highlightCellOptions(myGame.getPossibleCoordinates(null));
            }
            //update board with next possible move
            if (myGame.getPossibleCoordinates(clickedPiece).contains(nextMove)) {
                /* TODO: is not in check, or if selected move moves out of check, smt like accept move claus */
                myGame.movePiece(clickedPiece, nextMove);
                myGameView.updateChessCell(myGame.getSpot(clickedPiece));
                myLogger.log(Level.INFO, "MOVED");

                nextTurn();
            }
            myLogger.log(Level.WARNING, "Invalid Position Chosen");
            //display the possible neighbors of the first selected piece
//            else {
//                FIRSTCLICK = true;
//                clickedCoordinates(row, column);
//            }
        }

        // Increments turn and changes current player, also adds moves to history
        private void nextTurn () {
            numTurns++;
            currentPlayer = players[numTurns % 2];
            GameCoordinate[] moveRecord = {clickedPiece, nextMove};
            history.push(moveRecord);
        }

        /**
         *Uses most recent move to update the board backwards
         */
        @Override
        public void undoMove () {
            GameCoordinate[] recentMove = history.pop();
            myGame.movePiece(recentMove[1], recentMove[0]);
            numTurns -= numTurns;
        }

        /**
         * This should allow the player to change the rules using a menubar
         * The game should also be re-initialized without changing the current piece positions, csv will be ignored
         * @param variant
         */
        @Override
        public void changeVariant(String variant){
            //Here we will probably want to change up the Map of Data
            //Similar initialization mehtod but without changing the csv data
//        GameBoard currentLayout = new GameBoard(BOARDHEIGHT, BOARDWIDTH);
//        myBoard.getFullBoard();
            myData.put(myData.get("type"), variant);
//        myGame =  new Game(currentLayout,  myData);
            //needs to be more flexible, this can only change the type, which actually doesn't do anything
            //the conditions for the type of game should probably be stored in properties files unless we want
            //lots of menus for each of the types of change we could make

        }
    }

