package ooga.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;
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

import ooga.util.IncorrectCSVFormatException;

import ooga.view.ui.InformationPanel;

import ooga.view.ui.playerlogin.PlayerLoginInterface;
import ooga.view.ui.playerlogin.PlayerLoginView;
import ooga.view.ui.gameplaypanel.GameplayPanel;
import ooga.view.ui.controlpanel.ControlPanel;


/**
 * JavaFX View for each game that creates the general UI; each instance for a single game
 * application Relies on appropriate resourcebundles being configured as well as JavaFX Creates
 * gameController
 *
 * @author marcusdeans, drewpeterson
 */
public class GameView extends Application implements PanelListener, GameChessView {
  //JavaFX Simulation Parameters:
  private static final int FRAMES_PER_SECOND = 7;
  private static final double SECOND_DELAY = 7.0 / FRAMES_PER_SECOND;

  //General resource file structure
  private static final String GAME_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.GameViewResources";
  private static final ResourceBundle gameViewResources = ResourceBundle.getBundle(
      GAME_VIEW_RESOURCES_FILE_PATH);

  //Cosmetic features: colours and views
  private static final String GRID_COLORS_PATH = "ooga.view.viewresources.DefaultGridColours";
  private static final ResourceBundle defaultGridColours = ResourceBundle.getBundle(
      GRID_COLORS_PATH);

//  private final List<String> gameTypes = Arrays.asList(gameViewResources.getString("GameOptions").split(","));

  //Cosmetic features: JavaFX pixel positioning
  private int frameWidth;
  private int frameHeight;
  private int boardWidth;
  private int boardHeight;
  private Paint frameBackground;
  private int gridDisplayLength;
  private int[] gridSize;
  private String myFilename;
  private String NO_CONTENT = "None";

  //Information panel on top of screen
  private String myTitle;
  private String myType;
  private String myAuthor;

  //Control Panel on Right Side of Screen
  private int controlPanelX;
  private Node myVisualControlPanel;
  private Node myVisualGameplayPanel;
  private Node myVisualInformationPanel;
  private InformationPanel myInformationPanel;
  private GameplayPanel myGameplayPanel;

  //Gameplay Panel on Left Side of Screen
  private int gameplayPanelX;

  private int gameGridViewX;
  private int gameGridViewY;

  //Details panel on bottom of screen
  private String[] myGameParameters;
  private String myDescription;
  private String[] myGridColours;

  //Grid display
  private Node myGridPanel;

  //JavaFX setup elements
  private Stage myStage;
  private Timeline myAnimation;
  private Group myGameViewRoot;
  private Scene myGameViewScene;

  //Integral Game classes
  private GridView myGridView;
//  private GameController myGameController;

  private FileInputStream fis;

//  private static final String REQUIRED_PARAMETERS = "ooga.view.viewresources.requiredParameters";
//  private static final ResourceBundle requiredParameters = ResourceBundle.getBundle(
//      REQUIRED_PARAMETERS);

  private boolean successfulSetup;
  private Controller myChessController;


  /**
   * Creates new GameView for each application
   *
   * @param frameWidth      of JavaFX display in pixels
   * @param frameHeight     of JavaFX display in pixels
   * @param background colour of JavaFX background
   * @param filename   Filename of the simulation file which GameController uses
   * @param gameController the listener object that will be notified/called upon whenever the state of a UI panel changes due to user interaction
   */
  public GameView(int frameWidth, int frameHeight, int boardWidth, int boardHeight, String background, String filename, Controller gameController) {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    frameBackground = Color.web(background);
    myFilename = filename;
    myChessController = gameController;
    gridDisplayLength = frameWidth - getInt("width_buffer");
    controlPanelX = frameWidth - getInt("control_panel_offset");
    gameplayPanelX = getInt("gameplay_panel_offset");
    controlPanelX = frameWidth - getInt("control_panel_offset") + getInt("width_buffer");
    gameGridViewX = gameplayPanelX + getInt("button_width") + getInt("width_buffer");
    gameGridViewY = getInt("game_grid_y_offset");
    gridDisplayLength = controlPanelX - gameGridViewX - getInt("width_buffer")  - 2*getInt("line_offset");
    myGameViewRoot = new Group();
  }

  public static void highlightCellOptions(Set<Spot> possibleMoves) {
    //TODO: add highlight to possible neighbors
  }

  /**
   * Returns the PanelListener, allowing UI panel subclasses to interact with the listener
   * @return the PanelListener
   */
  protected Controller getGameController(){
    return myChessController;
  }

  // Initializes the controller and retrieves relevant parameters
  //TODO: make sure exception stops everything from running (maybe pass it up another level?)
  private void setupController(){
    successfulSetup = true;
//    try {
//      myGameController.setupProgram();
//      Map<String, String> parameters = myGameController.getConfigurationMap();
      //TODO; replace dummy
//      Map<String, String> parameters = new HashMap<>();
//      myTitle = parameters.get("Title");
//      myType = parameters.get("Type"); //work on translating from GameOfLife->life
//      myDescription = parameters.get("Description");
//      myAuthor = parameters.get("Author");
//      String[] myAdditionalParameters = requiredParameters.getString(myType).split(",");
//      myGameParameters = new String[myAdditionalParameters.length];
//      for(int iterate = 0; iterate < myAdditionalParameters.length; iterate++){
//        if(parameters.get(myAdditionalParameters[iterate]) != null){
//          myGameParameters[iterate] = String.format("%s = %s",myAdditionalParameters[iterate], parameters.get(myAdditionalParameters[iterate]));
//        }
//        else {
//          myGameParameters[iterate] = NO_CONTENT;
//        }
//      }
//
//      if (parameters.get("StateColors") != null) {
//        myGridColours = parameters.get("StateColors").split(",");
//      } else {
//        myGridColours = defaultGridColours.getString(myType).split(",");
//      }
////      gridSize = myGameController.getGridSize();
//      successfulSetup = true;
//    } catch(Exception e){
//      sendAlert(e.getMessage());
//    }
    myGameParameters = new String[1];
    myGameParameters[0] = "None";
    myType = "GameOfLife";
    myGridColours = defaultGridColours.getString("GameOfLife").split(",");

//    catch (IncorrectSimFormatException e) {
//      sendAlert(e.getMessage());
//    } catch (IncorrectCSVFormatException e) {
//      sendAlert(e.getMessage());
//    }
//    catch (ReflectionException e) {
//      sendAlert("InternalError Cannot Make Object");
//    }
  }

  /**
   * Start the JavaFX simulation by first setting up the scene and then initializing the animation
   *
   * @param primaryStage the Stage that will be used to display the game scene
   */
  @Override
  public void start(Stage primaryStage) {
    myStage = primaryStage;
    successfulSetup=true;
    if (successfulSetup){
      myAnimation = new Timeline();
      myAnimation.setCycleCount(Timeline.INDEFINITE);

      setupScene();
      primaryStage.setScene(myGameViewScene);
      primaryStage.setTitle(getWord("simulation_title"));
      primaryStage.show();
      myInformationPanel.adjustInformationPanelPosition();

//      myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
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
    myGameViewScene.getStylesheets().add(GameView.class.getResource("GameViewFormatting.css").toExternalForm());
    setupCheatCodes();
  }

  private void setupCheatCodes(){
    List<String> cheatCodeIdentifiers = List.of(getString("allCheatCodeIdentifiers").split(","));
    for(String kcIdentifier : cheatCodeIdentifiers){
      String rnIdentifier = getString(kcIdentifier);
      addCheatCode(new KeyCodeCombination(KeyCode.getKeyCode(kcIdentifier), KeyCombination.ALT_DOWN), rnIdentifier);
    }
  }

  private void addCheatCode(KeyCombination kc, String rnIdentifier){
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
  private Node createInformationPanel(){
    myInformationPanel = new InformationPanel(gameGridViewX, gridDisplayLength);
    myInformationPanel.setPanelListener(this);
    return myInformationPanel.createInformationPanel();
  }

  //create control panel on right of screen to control view, animation/gameplay, and loading/saving
  private Node createControlPanel(){
    ControlPanel newControlPanel = new ControlPanel(controlPanelX, myAnimation);
    newControlPanel.setPanelListener(this);
    return newControlPanel.createControlPanel();
  }

  //create gameplay panel on left of screen to control variant, move history, and dead pieces
  private Node createGameplayPanel(){
    myGameplayPanel = new GameplayPanel(gameplayPanelX);
    myGameplayPanel.setPanelListener(this);
    return myGameplayPanel.createGameplayPanel();
  }

  //initialize the grid itself that appears on the scree
  private Node createGrid() {
    myGridColours = defaultGridColours.getString("GameOfLife").split(",");
    myGridView = new GridView(boardHeight, boardWidth, myGridColours, gridDisplayLength, this);
    GridPane myGameGridView = myGridView.getMyGameGrid();
    myGameGridView.setLayoutX(gameGridViewX  + getInt("line_offset"));
    myGameGridView.setLayoutY(gameGridViewY  + getInt("line_offset"));
//    myGameController.setupListener(myGridView);
//    try {
//      myGameController.showInitialStates();
//    }
//    catch (ReflectionException e) {
//      sendAlert("InternalError Cannot Make Object");
//    }
    return myGameGridView;
  }


  private void updateGrid(double x, double y) {
//    myGameController.calculateIndexesAndUpdateModel(x, y, myGridView.getMyCellHeight(), myGridView.getMyCellWidth());
  }

  //<editor-fold desc="Setup Languages, Conversion, and Update on Change">
  //</editor-fold>


  // refreshes the UI panels by removing them from the scene before creating new panels and adding them back
  private void refreshUIPanels(){
    myGameViewRoot.getChildren().removeAll(myVisualGameplayPanel, myVisualControlPanel,
        myVisualInformationPanel);
    createUIPanels();
    myGameViewRoot.getChildren().addAll(myVisualGameplayPanel, myVisualControlPanel,
        myVisualInformationPanel);
    myStage.setTitle(getWord("simulation_title"));
  }

  /**
   * Updates the language displayed on the UI panels by first switching the default value of the Locale used to represent
   * the selected language and then refreshing the panels
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
   * @param newColor desired color scheme
   */
  @Override
  public void updateColorScheme(String newColor) {
    myGameViewScene.setFill(Color.web(newColor));
  }

  /**
   * Loads a new file by changing myFilename before resetting the controller and refreshing the grid view/UI panels
   * @param filename name of the file to load
   */
  @Override
  public void loadNewFile(String filename) throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException {
    myFilename = filename;
    myChessController.initializeFromFile(new File(myFilename));
  }

  /**
   * Saves the current simulation and its parameters to a .sim and .csv file
   */
  @Override
  public void saveCurrentFile(){
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
  public void undoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //TODO: callback to controller
    myChessController.undoMove();
  }
  @Override
  public void redoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //TODO: callback to controller
    myChessController.redoMove();
  }

  @Override
  public void changeVariant(String variant) {
    //TODO: callback to controller to change the variant
    myChessController.changeVariant(variant);
  }

  //compute which cell on the grid this corresponds to, NOT the pixel position
  //error check that its' in the board as well
  @Override
  public void getBoardClick(int column, int row) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //TODO: ensure controller callback works
    myChessController.clickedCoordinates(column, row);
  }

  @Override
  public void openPlayerLogin() {
    PlayerLoginInterface newPlayerLoginView = new PlayerLoginView();
    newPlayerLoginView.setPanelListener(this);
    newPlayerLoginView.start(new Stage());
  }

  @Override
  public void setNewPlayer(String username, String email, String password, int team, String colour) {
    myChessController.setPlayer(username, password, team, colour);
  }

  @Override
  public void closePlayerLogin(Stage stage) {
    stage.close();
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
   *  Allow controller to update the apperance of a specific cell on the chess board
   * @param spot the spot that will have a piece on it or not
   */
  @Override
  public void updateChessCell(Spot spot){
    myGridView.updateChessCell(spot);
  }

  /**
   * Allow controller to highlight a specific cell on the chess board
   * @param spot the cell that should be highlighted
   * @param hexColour
   */
  @Override
  public void colourChessCell(Spot spot, String hexColour) {myGridView.colourChessCell(spot, hexColour);}

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
    return "Yee";
  }

  // displays alert/error message to the user - currently duplicated in SharedUIComponents
  protected void sendAlert(String alertMessage) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(alertMessage);
    alert.show();
  }

  //retrieves relevant word from the "words" ResourceBundle
  protected String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = "error";
    try {
      value = words.getString(key);
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File %s", key,
          GAME_VIEW_RESOURCES_FILE_PATH));
    }
    return value;
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key){
    int value;
    try {
      value = Integer.parseInt(gameViewResources.getString(key));
    } catch(Exception e){
      value =-1;
    }
    return value;
  }

  //retrieves relevant word from the "words" ResourceBundle
  private String getString(String key) {
    String value;
    try {
      value = gameViewResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}