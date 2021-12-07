package ooga.controller;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.Spot;
import ooga.logic.game.Game;
import ooga.logic.game.Player;
import ooga.util.IncorrectCSVFormatException;
import ooga.view.GameView;


/**
 * The chess controller will handle taking in data using the file parsers and pass the information
 * to the model, as well as handling changed between the front end and the backend
 *
 * @author Carter Stonesifer
 */
public class ChessController implements Controller {

  private static final String PIECES_PACKAGE =
      ChessController.class.getPackageName() + ".controllerresources.";
  private static final String PUZZLE_CSV_MAP = "Puzzles";
  private GameView myGameView;
  private int BOARDWIDTH;
  private int BOARDHEIGHT;
  private Stack<GameCoordinate[]> history;
  private Stack<GameCoordinate[]> unwind;
  private Map<String, String> myData;
  private CSVParser myCSVParser = new CSVParser();
  private SIMParser mySIMParser = new SIMParser();
  private Game myGame;
  private boolean FIRSTCLICK = true;
  private GameCoordinate clickedPiece;
  private GameCoordinate nextMove;
  private int numTurns;
  private Player currentPlayer;
  private List<Player> thePlayers = new ArrayList<>();
  private int numPlayers;
  private int turnIterator;
  private ResourceBundle puzzleMap;
  private int puzzleNumber;
  private String puzzleName;

  private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private Map<Integer, Integer> myTempHashMap;

  private Map<String, String> myRulesMap;



  /**
   * Instantiates the chess controller and allow the game to be initiated.
   *
   * @param width
   * @param height
   * @param background
   * @param filename
   */
  public ChessController(int width, int height, String background, String filename) throws IOException {
    myGameView = new GameView(width, height, 8, 8, background, filename, this);
    myGame = new Game(height, width, new HashMap<>());
    myGameView.start(new Stage());

    myTempHashMap = new HashMap<>();
    myTempHashMap.put(0, 2);
    myTempHashMap.put(1, 1);
  }

  /**
   * Takes in the information from the SIM FILE, thus the CSV as well, and instantiates backend
   * classes with the required data
   *
   * @param file
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws IncorrectCSVFormatException
   */
  @Override
  public void initializeFromFile(File file)
      throws IOException, ClassNotFoundException,
      InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException, CsvValidationException {

    File simFile = new File(file.toString());
    myData = mySIMParser.readSimFile(simFile);
    File csvFile;
    if (myData.get("Type").equals("Puzzles")) {
      csvFile = puzzleBuild();
    } else {
      csvFile = new File(myData.get("GameConfiguration"));
    }
    myCSVParser.readCSVFile(csvFile);
    BOARDWIDTH = myCSVParser.getDimensions()[0];
    BOARDHEIGHT = myCSVParser.getDimensions()[1];
    myRulesMap = getRulesFromSim(myData);
    myGame = new Game(BOARDHEIGHT, BOARDWIDTH,myRulesMap);
    myGame.setGameType(myData.get("Type"));
    if (myData.get("Type").equals("Puzzles")) {
      myGame.setPuzzleSolution(puzzleMap.getString(puzzleName));
    }
    myGame.setEdgePolicy(myData.get("EdgePolicy"));
    boardInitializer(myCSVParser.getInitialStates(), myGame);
    boardViewBuild(myGame);
    numTurns = 1;
    setPlayer(1, "Player1", "Password1", 1, "#012169");
    setPlayer(2, "Player2", "Password2", 2, "#00539B");
    history = new Stack<GameCoordinate[]>();
    unwind = new Stack<GameCoordinate[]>();
    myLogger.log(Level.INFO, "Inititalized: " + myData.get("Type") + " gametype");
  }

  private File puzzleBuild() {
    File csvFile;
    puzzleMap = ResourceBundle.getBundle(PIECES_PACKAGE + PUZZLE_CSV_MAP);
    puzzleNumber = 1 + new Random().nextInt(Integer.parseInt(puzzleMap.getString("numPuzzles")));
    puzzleName = puzzleMap.getString(Integer.toString(puzzleNumber));
    csvFile = new File(puzzleName);
    return csvFile;
  }

  public void boardInitializer(String[][] initialStates, Game game)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    for (int rowPosition = 0; rowPosition < BOARDWIDTH; rowPosition++) {
      for (int columnPosition = 0; columnPosition < BOARDHEIGHT; columnPosition++) {
        game.setupBoard(initialStates[rowPosition][columnPosition], rowPosition,
            columnPosition); //parameter change to be done
      }
    }
  }

  // Rebuilds the board to update it
  private void boardViewBuild(Game game) {
    List<Spot> fullBoard = game.getFullBoard();
    fullBoard.stream().forEach(spot -> myGameView.updateChessCell(spot));
  }

  /**
   * Gives BOARDHEIGHT
   *
   * @return
   */
  @Override
  public int getHeight() {
    return BOARDHEIGHT;
  }

  @Override
  public boolean setPlayer(int playerIdentifier, String userName, String password, int team, String color) throws IOException {
    Player addPlayer = new Player(playerIdentifier, userName, password, team);
    thePlayers.add(addPlayer);
    myLogger.log(Level.INFO, "Welcome: "+ addPlayer.getUsername());
    currentPlayer = thePlayers.get(0);
    numPlayers = thePlayers.size();
    return true; //TODO: change to returning appropriate value if player created
  }

  @Deprecated
  public void setPlayer(String userName, int team) {
    //Player addPlayer = new Player(userName, team);
    //thePlayers.add(addPlayer);
  }

  @Override
  public Player getPlayer(int playerIdentifier){
    return thePlayers.get(playerIdentifier);
  }


  /**
   * Gives BOARDWIDTH
   *
   * @return
   */
  @Override
  public int getWidth() {
    return BOARDWIDTH;
  }

  @Override
  public void resetGame() {
    myGame.reset();
    boardViewBuild(myGame);
  }

  @Override
  public void setTime(int speed) {

  }

  @Override
  public void clickedCoordinates(int row, int column)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (FIRSTCLICK) {
      handleFirstClick(row, column);
    } else {
      handleSecondClick(row, column);
    }
  }

  private void handleFirstClick(int row, int column) {
    clickedPiece = new GameCoordinate(row, column);
    myGame.setSelected(clickedPiece);
    if (myGame.getSpot(clickedPiece).getPiece() != null) {
      if (myTempHashMap.get(turnIterator) == myGame.getSpot(clickedPiece).getPiece().getTeam()) {
        Set<Spot> test = myGame.getPossibleCoordinates(clickedPiece, currentPlayer.getTeam());
        highlightSpots(test);
        myGameView.colourChessCell(myGame.getSpot(clickedPiece),myData.get("MoveColor"));
        myLogger.log(Level.INFO, "FIRST CLICK");
        FIRSTCLICK = false;
      }
    }
  }

  private void highlightSpots(Set<Spot> possibleCoordinates) {
    for (Spot s : possibleCoordinates) {
      //TODO: fix highlight colour
      myGameView.colourChessCell(s, myData.get("SelectColor"));
    }
  }

  private void handleSecondClick(int row, int column)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    nextMove = new GameCoordinate(row, column);
    //clicking same piece to deselect
    if (nextMove.equals(clickedPiece)) {
      myLogger.log(Level.INFO, "SAME PIECE");
      FIRSTCLICK = true;
      boardViewBuild(myGame);
    }
    //update board with next possible move
    else if (myGame.getPossibleCoordinates(clickedPiece, 1).contains(myGame.getSpot(nextMove))) {
      myGame.movePiece(clickedPiece, nextMove);
      myGameView.updateChessCell(myGame.getSpot(clickedPiece));
      FIRSTCLICK = true;
      myGameView.addHistory(clickedPiece.getX_pos() + "," + clickedPiece.getY_pos() + " -> "+ nextMove.getX_pos() + "," + nextMove.getY_pos());
      numTurns++;
      unwind.clear();
      nextTurn(clickedPiece, nextMove);
    } else {
      myLogger.log(Level.WARNING, "INVALID PIECE SELECTED");
    }
  }

  // Increments turn and changes current player, also adds moves to history
  private void nextTurn(GameCoordinate original, GameCoordinate next) {
    switchPlayers();
    GameCoordinate[] moveRecord = {original, next};
    history.push(moveRecord);
    myLogger.log(Level.INFO,
        "MOVED: " + moveRecord[0].getX_pos() + "," + moveRecord[0].getY_pos() + " to "
            + moveRecord[1].getX_pos() + "," + moveRecord[1].getY_pos());
    boardViewBuild(myGame);
  }

  /**
   * Uses most recent move to update the board backwards
   */
  @Override
  public void undoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (!history.isEmpty()) {
      GameCoordinate[] recentMove = history.pop();
      unwind.push(recentMove);
      myLogger.log(Level.INFO,
          "MOVED: " + recentMove[0].getX_pos() + "," + recentMove[0].getY_pos() + " to "
              + recentMove[1].getX_pos() + "," + recentMove[1].getY_pos());
      myGame.movePiece(recentMove[1], recentMove[0]);
      myGameView.removeHistory();
      numTurns -= numTurns;
      switchPlayers();
      boardViewBuild(myGame);
    }
  }

  @Override
  public void acceptCheatCode(String identifier){
    Method action;
    ResourceBundle cheatCodeMethods = ResourceBundle.getBundle("src/ooga/view/viewresources/GameViewResources.properties");
    String method = cheatCodeMethods.getString(identifier);
    try {
      action = ChessController.class.getDeclaredMethod(method, Class.forName(identifier));
      action.invoke(this, identifier);
    } catch (Exception e) {
      myLogger.log(Level.WARNING,"Method does not exist");
    }
    myLogger.log(Level.INFO, "Cheat code "+ identifier+" activated");
  }


  @Override
  public void redoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (!unwind.isEmpty()) {
      GameCoordinate[] reDoneMove = unwind.pop();
      history.push(reDoneMove);
      myLogger.log(Level.INFO,
          "MOVED: " + reDoneMove[1].getX_pos() + "," + reDoneMove[1].getY_pos() + " to "
              + reDoneMove[0].getX_pos() + "," + reDoneMove[0].getY_pos());
      myGame.movePiece(reDoneMove[0], reDoneMove[1]);
      myGameView.addHistory(reDoneMove[0].getX_pos() + "," + reDoneMove[0].getY_pos() + " -> "
              + reDoneMove[1].getX_pos() + "," + reDoneMove[1].getY_pos());
      numTurns += numTurns;
      switchPlayers();
      boardViewBuild(myGame);
    }
  }

  /**
   * This should allow the player to change the rules using a menubar The game should also be
   * re-initialized without changing the current piece positions, csv will be ignored
   *
   * @param variant
   */
  @Override
  public void changeVariant(String variant) {
    myData.put(myData.get("type"), variant);
  }

  private void switchPlayers() {
    turnIterator = (turnIterator + 1) % numPlayers;
    currentPlayer = thePlayers.get(turnIterator);
  }

  private Map<String, String> getRulesFromSim(Map<String, String> myData) {
    myRulesMap = new HashMap<>();
    for (String key : myData.keySet()){
      if (key.substring(0,1).equals("^")){
        myRulesMap.put(key.substring(1), myData.get(key));
      }
    }

    return myRulesMap;
  }
  //____________________________________________________CHEAT CODES__________________________________________________

  private void Cannibalism(){

  }
  private void IgnoreFilters(){

  }
  private void InstantEnd(){

  }
  private void ToroidalYAxis(){

  }
  private void PawnsToQueens(){

  }
  private void PawnsToRooks(){

  }
  private void PawnsToKnights(){

  }
  private void PawnsToBishops(){

  }
  private void JumpingPieces(){

  }
  private void VisiblePortals(){

  }
  private void VisibleBlackHoles(){

  }
  private void OpeningAlpha(){

  }
  private void OpeningBravo(){

  }
  private void OpeningCharlie(){

  }
  private void OpeningDelta(){

  }
  private void OpeningEcho(){

  }
}

