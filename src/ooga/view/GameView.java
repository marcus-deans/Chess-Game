package ooga.view;

import static ooga.view.GameViewUtil.getInt;
import static ooga.view.GameViewUtil.getString;
import static ooga.view.GameViewUtil.getWord;

import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.spot.Spot;
import ooga.logic.game.Player;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.ResourceRetriever;
import ooga.view.ui.InformationPanel;
import ooga.view.ui.controlpanel.ControlPanel;
import ooga.view.ui.gameplaypanel.GameplayPanel;
import ooga.view.ui.playerlogin.PlayerLoginInterface;
import ooga.view.ui.playerlogin.PlayerLoginView;
import ooga.view.ui.playerprofile.PlayerProfileInterface;
import ooga.view.ui.playerprofile.PlayerProfileView;


/**
 * JavaFX View for each game that creates the general UI; each instance for a single game
 * application Relies on appropriate resourcebundles being configured as well as JavaFX Creates
 * gameController
 *
 * @author marcusdeans
 */
public class GameView extends Application implements PanelListener, GameChessView {

  //Cosmetic features: colours and views
  private static final String GRID_COLORS_PATH = "ooga.view.viewresources.DefaultGridColours";
  private static final ResourceBundle defaultGridColours = ResourceBundle.getBundle(
      GRID_COLORS_PATH);

  //JavaFX setup elements
  private Stage myStage;
  private Timeline myAnimation;
  private Group myGameViewRoot;
  private Scene myGameViewScene;

  //Cosmetic features: JavaFX pixel positioning
  private int frameWidth;
  private int frameHeight;
  private int boardWidth;
  private int boardHeight;
  private Paint frameBackground;
  private int gridDisplayLength;
  private String myFilename;

  //Control Panel on Right Side of Screen
  private int controlPanelX;
  private Node myVisualControlPanel;
  private ControlPanel myControlPanel;

  //Gameplay Panel on Left Side of Screen
  private GameplayPanel myGameplayPanel;
  private Node myVisualGameplayPanel;
  private int gameplayPanelX;

  //Information Panel on top of Screen
  private Node myVisualInformationPanel;
  private InformationPanel myInformationPanel;
  private String myDescription;
  private String[] myGridColours;

  //Grid display
  private Node myGridPanel;
  private int gameGridViewX;
  private int gameGridViewY;

  //Integral Game classes
  private GridChessView myGridView;

  private FileInputStream fis;

  private boolean successfulSetup;
  private Controller myChessController;

  /**
   * Creates new GameView for each application
   *
   * @param frameWidth     of JavaFX display in pixels
   * @param frameHeight    of JavaFX display in pixels
   * @param background     colour of JavaFX background
   * @param filename       Filename of the simulation file which GameController uses
   * @param gameController the listener object that will be notified/called upon whenever the state
   *                       of a UI panel changes due to user interaction
   */
  public GameView(int frameWidth, int frameHeight, int boardWidth, int boardHeight,
      String background, String filename, String description, Controller gameController) {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    frameBackground = Color.web(background);
    myDescription = description;
    myFilename = filename;
    myChessController = gameController;
    determineElementXPositioning(frameWidth);
    myGameViewRoot = new Group();
  }

  //Compute the x positions of the various view elements based on the specified screen size
  private void determineElementXPositioning(int frameWidth) {
    gridDisplayLength = frameWidth - getInt("width_buffer");
    controlPanelX = frameWidth - getInt("control_panel_offset");
    gameplayPanelX = getInt("gameplay_panel_offset");
    controlPanelX = frameWidth - getInt("control_panel_offset") + getInt("width_buffer");
    gameGridViewX = gameplayPanelX + getInt("button_width") + getInt("width_buffer");
    gameGridViewY = getInt("game_grid_y_offset");
    gridDisplayLength =
        controlPanelX - gameGridViewX - getInt("width_buffer") - 2 * getInt("line_offset");
  }


  /**
   * Start the JavaFX simulation by first setting up the scene and then initializing the animation
   *
   * @param primaryStage the Stage that will be used to display the game scene
   */
  @Override
  public void start(Stage primaryStage) {
    myStage = primaryStage;
    successfulSetup = true;
    if (successfulSetup) {
      myAnimation = new Timeline();
      myAnimation.setCycleCount(Timeline.INDEFINITE);

      setupScene();
      primaryStage.setScene(myGameViewScene);
      primaryStage.setTitle(getWord("simulation_title"));
      primaryStage.show();
      myInformationPanel.adjustInformationPanelPosition();
    }
  }

  // creates the scene and initializes all of its components
  private void setupScene() {
    myGameViewRoot = new Group();
    myGameViewScene = new Scene(myGameViewRoot, frameWidth, frameHeight, frameBackground);

    createUIPanels();

    myGridPanel = createGrid();
    myGameViewRoot.getChildren().addAll(myVisualGameplayPanel, myVisualControlPanel,
        myVisualInformationPanel, myGridPanel);
    myGameViewScene.getStylesheets()
        .add(GameView.class.getResource("GameViewFormatting.css").toExternalForm());
    setupCheatCodes();
  }

  //retrieve all of the cheat codes and setup the desired combinations that will be created
  private void setupCheatCodes() {
    List<String> cheatCodeIdentifiers = List.of(getString("allCheatCodeIdentifiers").split(","));
    for (String kcIdentifier : cheatCodeIdentifiers) {
      String rnIdentifier = getString(kcIdentifier);
      addCheatCode(
          new KeyCodeCombination(KeyCode.getKeyCode(kcIdentifier), KeyCombination.ALT_DOWN),
          rnIdentifier);
    }
  }

  //create an inidivudal cheat code by creatinga  javaFX accelator and Runnable
  private void addCheatCode(KeyCombination kc, String rnIdentifier) {
    Runnable rn = () -> myChessController.acceptCheatCode(rnIdentifier);
    myGameViewScene.getAccelerators().put(kc, rn);
  }

  //create the UI panels that will provide interactivity and information to the user
  private void createUIPanels() {
    // Gameplay (left) panel
    myVisualGameplayPanel = createGameplayPanel();

    // Control (right) panel:
    myVisualControlPanel = createControlPanel();

    // Information (top) panel:
    myVisualInformationPanel = createInformationPanel();
  }

  //create information panel on top of screen to display title as well as user information
  private Node createInformationPanel() {
    myInformationPanel = new InformationPanel(gameGridViewX, gridDisplayLength);
    myInformationPanel.setPanelListener(this);
    return myInformationPanel.createInformationPanel();
  }

  //create control panel on right of screen to control view, animation/gameplay, and loading/saving
  private Node createControlPanel() {
    myControlPanel = new ControlPanel(controlPanelX, myAnimation);
    myControlPanel.setPanelListener(this);
    return myControlPanel.createControlPanel();
  }

  //create gameplay panel on left of screen to control variant, move history, and dead pieces
  private Node createGameplayPanel() {
    myGameplayPanel = new GameplayPanel(gameplayPanelX, myDescription);
    myGameplayPanel.setPanelListener(this);
    return myGameplayPanel.createGameplayPanel();
  }

  //initialize the grid itself that appears on the scree
  private Node createGrid() {
    myGridColours = defaultGridColours.getString("GameOfLife").split(",");
    myGridView = new GridView(boardHeight, boardWidth, myGridColours, gridDisplayLength, this);
    GridPane myGameGridView = myGridView.getMyGameGrid();
    myGameGridView.setLayoutX(gameGridViewX + getInt("line_offset"));
    myGameGridView.setLayoutY(gameGridViewY + getInt("line_offset"));
    return myGameGridView;
  }

  // refreshes the UI panels by removing them from the scene before creating new panels and adding them back
  private void refreshUIPanels() {
    myGameViewRoot.getChildren().removeAll(myVisualGameplayPanel, myVisualControlPanel,
        myVisualInformationPanel);
    createUIPanels();
    myGameViewRoot.getChildren().addAll(myVisualGameplayPanel, myVisualControlPanel,
        myVisualInformationPanel);
    myStage.setTitle(getWord("simulation_title"));
  }

  /**
   * Updates the language displayed on the UI panels by first switching the default value of the
   * Locale used to represent the selected language and then refreshing the panels
   *
   * @param newLanguage the desired language
   */
  @Override
  public void updateLanguage(String newLanguage) {
    switch (newLanguage) {
      case "English" -> {
        Locale.setDefault(new Locale("en"));
      }
      case "Spanish" -> {
        Locale.setDefault(new Locale("es"));
      }
      case "French" -> {
        Locale.setDefault(new Locale("fr"));
      }
      case "Latin" -> {
        Locale.setDefault(new Locale("la"));
      }
      case "German" -> {
        Locale.setDefault(new Locale("de"));
      }
    }
    refreshUIPanels();
  }

  /**
   * Resets the simulation on the screen by simply reloading the current file
   */
  @Override
  public void resetGame() {
    myChessController.resetGame();
  }

  /**
   * Updates the color scheme by simply setting the fill of the background
   *
   * @param newColor desired color scheme
   */
  @Override
  public void updateColorScheme(String newColor) {
    myGameViewScene.setFill(Color.web(newColor));
  }

  /**
   * Loads a new file by changing myFilename before resetting the controller and refreshing the grid
   * view/UI panels
   *
   * @param filename name of the file to load
   */
  @Override
  public void loadNewFile(String filename)
      throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException {
    myFilename = filename;
    myChessController.initializeFromFile(new File(myFilename));
  }

  /**
   * Saves the current simulation and its parameters to a .sim and .csv file
   */
  @Override
  public void saveCurrentFile() {
    // TODO: does NOT currently work
//    String filename = getUserSaveFileName(getWord("get_user_filename"));
//    if (myGameController.saveCommand(filename)) {
////          updateSavedDropdown();
//    }
//    else {
//      sendAlert("Error Saving Program");
//    }
  }

  /**
   * Undo the previous move
   */
  @Override
  public void undoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //TODO: callback to controller
    myChessController.undoMove();
  }

  /**
   * Redo the previously undone move
   */
  @Override
  public void redoMove()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //TODO: callback to controller
    myChessController.redoMove();
  }

  /**
   * Allows a user-selected cheat code to be propagated to the controller
   * @param cheatCode the desired cheat code that will be applied
   */
  @Override
  public void selectCheatCode(String cheatCode) {
    myChessController.acceptCheatCode(cheatCode);
  }


  /**
   * Send coordinates that the user has clicked to the controller for computation
   * @param column the column of the chessboard that the click was on
   * @param row the row of the chess board tha the click waso n
   */
  @Override
  public void getBoardClick(int column, int row)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myChessController.clickedCoordinates(column, row);
  }

  /**
   * Open the player login pane so that the user can create their profile
   * @param playerIdentifier the unique identifier of the player
   */
  @Override
  public void openPlayerLogin(int playerIdentifier) {
    PlayerLoginInterface newPlayerLoginView = new PlayerLoginView(playerIdentifier);
    newPlayerLoginView.setPanelListener(this);
    newPlayerLoginView.start(new Stage());
  }

  /**
   * Define a new player as created by the user
   * @param playerIdentifier the unique identifier of thep layer
   * @param username the user-specified name
   * @param email user-specified email
   * @param password user-especified password
   * @param team the string of the user, either white or black
   * @param colour the desired profile coloru of the user
   * @return whether the creation of hte player was succesful as reported by the controller
   */
  @Override
  public boolean setNewPlayer(int playerIdentifier, String username, String email, String password,
      int team, String colour) throws IOException {
    return myChessController.setPlayer(playerIdentifier, username, password, team, colour);
  }

  /**
   * Close the player login pane now that the userh as logged in
   * @param stage the login stage which now is closed
   * @param playerIdentifier the unique identifier of hte player
   */
  @Override
  public void closePlayerLogin(Stage stage, int playerIdentifier) {
    stage.close();
    myControlPanel.playerHasLoggedIn(playerIdentifier);
  }

  /**
   * Open the profile of the specified player
   * @param playerIdentifier the iunique identifier of hte player
   */
  @Override
  public void openPlayerProfile(int playerIdentifier) {
    Player currentPlayer = myChessController.getPlayer(playerIdentifier);
    PlayerProfileInterface newPlayerProfileView = new PlayerProfileView(currentPlayer);
    newPlayerProfileView.setPanelListener(this);
    newPlayerProfileView.start(new Stage());
  }

  /**
   * Add to the history of past moves
   *
   * @param historyText
   */
  @Override
  public void addHistory(String historyText) {
    myGameplayPanel.updateHistory(historyText);
  }

  //get the filename for the simulation file that the user wants to save the current simulation to
  private String getUserSaveFileName(String message) {
    // TODO: implement interface from gamecontroller
    myAnimation.pause();
//    TextInputDialog getUserInput = new TextInputDialog();
//    getUserInput.setHeaderText(message);
//    String fileName = getUserInput.showAndWait().toString();
//    if (myGameController.validateSaveStringFilenameUsingIO(fileName)) {
//      return fileName;
//    }
//    sendAlert("Invalid Filename");
//    myAnimation.play();
//    return getUserSaveFileName(
//        message); //TODO: test to make sure this gives users another chance if they submit an invalid filename
    return getWord("player");
  }

  /**
   * Remove from the history of past moves
   */
  @Override
  public void removeHistory() {
    myGameplayPanel.removeHistory();
  }

  /**
   * Update the graveyard of dead pieces
   */
  @Override
  public void updateGraveyard(Piece deadPiece) {
    myGameplayPanel.updateGraveyard(deadPiece);
  }

  /**
   * Allow controller to update the apperance of a specific cell on the chess board
   *
   * @param spot the spot that will have a piece on it or not
   */
  @Override
  public void updateChessCell(Spot spot) {
    myGridView.updateChessCell(spot);
  }

  /**
   * Display which team has won the game which is determined by the backend
   * @param teamNumber the team that won
   */
  @Override
  public void displayGameComplete(int teamNumber) {
    String teamName;
    if (teamNumber == 1) {
      teamName = "White";
    } else {
      teamName = "Black";
    }
    ResourceRetriever.showAlert(Alert.AlertType.INFORMATION, getWord("game_complete_title"),
        String.format("%s %s", teamName, getWord("game_complete_message")));
    resetGame();
  }

  /**
   * Allow controller to highlight a specific cell on the chess board
   *
   * @param spot      the cell that should be highlighted
   * @param hexColour
   */
  @Override
  public void colourChessCell(Spot spot, String hexColour) {
    myGridView.colourChessCell(spot, hexColour);
  }

  /**
   * Set the board description which is available to the user
   * @param boardDescription the text description
   */
  @Override
  public void setBoardDescription(String boardDescription) {
    myDescription = boardDescription;
    myGameplayPanel.setBoardDescription(myDescription);
  }

}