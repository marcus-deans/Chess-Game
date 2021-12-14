package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
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
import javafx.stage.Stage;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.game.Game;
import ooga.logic.game.Player;
import ooga.util.IncorrectCSVFormatException;
import ooga.view.GameChessView;
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
  private static final String GAME_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.GameViewResources";
  private static final String CHESS_CONTROLLER_RESOURCES_PATH = "ooga.controller.controllerresources.ControllerResources";
  private static final ResourceBundle controllerResources = ResourceBundle.getBundle(CHESS_CONTROLLER_RESOURCES_PATH);
  private final String[] ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
  private GameChessView myGameView;
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
  public ChessController(int width, int height, String background, String filename)
      throws IOException {
    myGameView = new GameView(width, height, 8, 8, background, filename, filename, this);
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
    myGame = new Game(BOARDHEIGHT, BOARDWIDTH, myRulesMap);
    myGame.setGameType(myData.get("Type"));
    setBoardDescription(myData.get("Description"));
    if (myData.get("Type").equals("Puzzles")) {
      myGame.setPuzzleSolution(puzzleMap.getString(puzzleName));
    }
    myGame.setEdgePolicy(myData.get("EdgePolicy"));
    boardInitializer(myCSVParser.getInitialStates(), myGame);
    boardViewBuild(myGame);
    numTurns = 1;
    history = new Stack<GameCoordinate[]>();
    unwind = new Stack<GameCoordinate[]>();
    myLogger.log(Level.INFO, "Inititalized: " + myData.get("Type") + " gametype");
  }

  private void setBoardDescription(String boardDescription) {
    myGameView.setBoardDescription(boardDescription);
  }

  private File puzzleBuild() {
    File csvFile;
    puzzleMap = ResourceBundle.getBundle(PIECES_PACKAGE + PUZZLE_CSV_MAP);
    puzzleNumber = 1 + new Random().nextInt(Integer.parseInt(puzzleMap.getString("numPuzzles")));
    puzzleName = puzzleMap.getString(Integer.toString(puzzleNumber));
    csvFile = new File(puzzleName);
    return csvFile;
  }

  private void boardInitializer(String[][] initialStates, Game game)
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

  /**
   * Allows a new Player to be added to the Roster of Players
   *
   * @param userName
   * @param password
   * @param team
   * @param color
   * @throws IOException
   */
  @Override
  public boolean setPlayer(int playerIdentifier, String userName, String password, int team,
      String color) throws IOException {
    Player addPlayer = new Player(playerIdentifier, userName, password, team);
    thePlayers.add(addPlayer);
    currentPlayer = thePlayers.get(0);
    numPlayers = thePlayers.size();
    boolean result = addPlayer.checkUser();
    addPlayer.setProfileColor(color);
    return result;
  }

  @Deprecated
  public void setPlayer(String userName, int team) {
    //Player addPlayer = new Player(userName, team);
    //thePlayers.add(addPlayer);
  }

  @Override
  public Player getPlayer(int playerIdentifier) {
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

  /**
   * Clears History and replaces Game Board with the initial setup
   */
  @Override
  public void resetGame() {
    myGame.reset();
    while (!history.isEmpty()) {
      history.pop();
      myGameView.removeHistory();
    }
    numTurns = 1;
    boardViewBuild(myGame);
    myLogger.log(Level.INFO, "BOARD RESET");
  }

  @Override
  public void setTime(int speed) {

  }

  /**
   * Handles User click input and determines which piece and selected and the action taken by the
   * user First click selects a piece belonging to the player and highlights possible move options
   * based on the variation Second click either deselects the piece if clicked for the second time,
   * does nothing if selection is outside of range of moves, or moves the piece to the desired
   * position.
   *
   * @param row
   * @param column
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
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
        myGameView.colourChessCell(myGame.getSpot(clickedPiece), myData.get("MoveColor"));
        myLogger.log(Level.INFO, currentPlayer.getUsername() + "'s turn!");

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
      myGameView.addHistory(
          ALPHABET[clickedPiece.getX_pos()] + "," + (clickedPiece.getY_pos() + 1) + " -> "
              + ALPHABET[nextMove.getX_pos()] + "," + (nextMove.getY_pos() + 1));
      numTurns++;
      unwind.clear();
      //TODO IS GAME OVER: (update user score: winner ->true, loser ->false) (check isGameOver from Game)
      try {
        checkEndGame();
      } catch (Exception e) {
        myLogger.log(Level.SEVERE, "FAILURE TO SELECT PIECE");
      }
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
        "MOVED: " + historyNotation(moveRecord, true));
    boardViewBuild(myGame);
  }

  private void checkEndGame() throws IOException {
    if (myGame.getIsGameOver()) {
      myGameView.displayGameComplete(currentPlayer.getTeam());
      currentPlayer.updateUserScore(true);
      for (Player player : thePlayers) {
        if (player != currentPlayer) {
          player.updateUserScore(false);
        }
      }
      currentPlayer = thePlayers.get(0);
      myGame.setIsGameOver(false);
    }
  }

  /**
   * Undoes the previous move
   */
  @Override
  public void undoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (!history.isEmpty()) {
      GameCoordinate[] recentMove = history.pop();
      unwind.push(recentMove);
      myLogger.log(Level.INFO,
          "MOVED: " + historyNotation(recentMove, false));
      myGame.movePiece(recentMove[1], recentMove[0]);
      myGameView.removeHistory();
      numTurns -= numTurns;
      switchPlayers();
      boardViewBuild(myGame);
    }
  }

  /**
   * Uses reflection and cheat code (Alt + letter combination) to change board conditions
   *
   * @param identifier
   */
  @Override
  public void acceptCheatCode(String identifier) {
    Method action;
    ResourceBundle cheatCodeMethods = ResourceBundle.getBundle(GAME_VIEW_RESOURCES_FILE_PATH);
    //ResourceBundle cheatCodeMethods = ResourceBundle.getBundle("src/ooga/view/viewresources/GameViewResources.properties");
    String method = identifier;
    try {
      action = ChessController.class.getDeclaredMethod(method, null);
      action.invoke(this);
      myLogger.log(Level.INFO, "Cheat code " + identifier + " activated");
    } catch (Exception e) {
      myLogger.log(Level.WARNING, "Method does not exist");
    }
  }


  /**
   * Allows user to replace pieces after undoing a move
   *
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  @Override
  public void redoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (!unwind.isEmpty()) {
      GameCoordinate[] reDoneMove = unwind.pop();
      history.push(reDoneMove);
      myLogger.log(Level.INFO,
          "MOVED: " + historyNotation(reDoneMove, true));
      myGameView.addHistory(historyNotation(reDoneMove, true));
      numTurns += numTurns;
      switchPlayers();
      boardViewBuild(myGame);
    }
  }

  private String historyNotation(GameCoordinate[] change, boolean forward) {
    int first;
    int second;
    if (forward) {
      first = 0;
      second = 1;
    } else {
      first = 1;
      second = 0;
    }

    String call = ALPHABET[change[first].getX_pos()] + "," + (change[first].getY_pos() + 1) + " -> "
        + ALPHABET[change[second].getX_pos()] + "," + (change[second].getY_pos() + 1);
    return call;
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
    turnIterator = (turnIterator + 1) % (numPlayers);
    currentPlayer = thePlayers.get(turnIterator);
  }

  private Map<String, String> getRulesFromSim(Map<String, String> myData) {
    myRulesMap = new HashMap<>();
    for (String key : myData.keySet()) {
      if (key.substring(0, 1).equals("^")) {
        myRulesMap.put(key.substring(1), myData.get(key));
      }
    }

    return myRulesMap;
  }
  //____________________________________________________CHEAT CODES__________________________________________________

  /**
   * Allows player to enable "friendly fire" and attacks own pieces
   */
  public void Cannibalism() {
    myGame.makePiecesCannibalize();
  }

  /**
   *
   */
  private void IgnoreFilters() {
    myGame.setFilter(false);
  }

  /**
   *
   */

  private void InstantEnd()
      throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File("data/CheatCode.sim");
    initializeFromFile(file);
  }

  /**
   * Allowing Toroidal Game Variant to wrap around the North and South sides opposed to East and
   * West
   */
  private void ToroidalYAxis() {
    myGame.setEdgePolicy("CheatCode");
  }

  private void PawnsToQueens()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myGame.pawnsToPiece("Q");
    boardViewBuild(myGame);
  }

  private void PawnsToRooks()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myGame.pawnsToPiece("R");
    boardViewBuild(myGame);
  }

  /**
   * Change all Pawns to Knights
   */
  private void PawnsToKnights()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myGame.pawnsToPiece("N");
    boardViewBuild(myGame);
  }

  /**
   * Change all Pawns to Bishops
   */
  private void PawnsToBishops()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myGame.pawnsToPiece("B");
    boardViewBuild(myGame);
  }

  /**
   * Allow pieces to jump each other like horses
   */
  private void JumpingPieces() {
    myGame.makePiecesJump();
  }

  /**
   * Highlight Portals on the board
   */
  private void VisiblePortals() {
    List<Spot> allPortals = new ArrayList<>();
    for (Spot s : myGame.getFullBoard()) {
      if (((GameSpot) s).getTypeOfSpot() == 1) {
        allPortals.add(s);
      }
    }
    for (Spot q : allPortals) {
      myGameView.colourChessCell(q, "#FF0000");
    }
  }

  /**
   * Highlight Blackholes on the board
   */
  private void VisibleBlackHoles() {
    List<Spot> allBlackHoles = new ArrayList<>();
    for (Spot s : myGame.getFullBoard())
    {
      if(((GameSpot) s).getTypeOfSpot()==2)
      {
        allBlackHoles.add(s);
      }
    }
    for(Spot q : allBlackHoles){
      myGameView.colourChessCell(q, "#000000");
    }
  }

  /**
   *
   */
  private void PawnBattle() throws
      CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File("data/PawnBattle.sim");
    initializeFromFile(file);
  }

  /**
   *
   */
  private void OpeningBravo() throws
      CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File(controllerResources.getString("Bravo"));
    initializeFromFile(file);
  }

  /**
   *
   */
  private void OpeningCharlie() throws
      CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File(controllerResources.getString("Charlie"));
    initializeFromFile(file);

  }

  /**
   *
   */
  private void OpeningDelta() throws
      CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File(controllerResources.getString("Delta"));
    initializeFromFile(file);

  }

  /**
   *
   */
  private void OpeningEcho() throws
      CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    File file = new File(controllerResources.getString("Echo"));
    initializeFromFile(file);

  }
}

