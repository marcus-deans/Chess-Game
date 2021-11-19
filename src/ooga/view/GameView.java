package ooga.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.logic.board.spot.Spot;
import ooga.view.ui.gameplaypanel.GameplayPanel;
import ooga.view.ui.controlpanel.ControlPanel;


/**
 * JavaFX View for each game that creates the general UI; each instance for a single game
 * application Relies on appropriate resourcebundles being configured as well as JavaFX Creates
 * gameController
 *
 * @author marcusdeans, drewpeterson
 */
public class GameView extends Application implements PanelListener {
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
  private Paint frameBackground;
  private int gridDisplayLength;
  private int[] gridSize;
  private String myFilename;
  private String NO_CONTENT = "None";

  //Information panel on top of screen
  private String myTitle;
  private String myType;
  private String myAuthor;
  private Node myInfoPanel;

  //Control Panel on Right Side of Screen
  private int controlPanelX;
  private Node myControlPanel;
  private Node myGameplayPanel;
  private Node myViewControlPanel;
  private Node myAnimationControlPanel;
  private Node myLoadControlPanel;

  //Details panel on bottom of screen
  private String[] myGameParameters;
  private String myDescription;
  private String[] myGridColours;
  private Node myDetailsPanel;

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
   * @param width      of JavaFX display in pixels
   * @param height     of JavaFX display in pixels
   * @param background colour of JavaFX background
   * @param filename   Filename of the simulation file which GameController uses
   * @param gameController the listener object that will be notified/called upon whenever the state of a UI panel changes due to user interaction
   */
  public GameView(int width, int height, String background, String filename, Controller gameController) {
    frameWidth = width;
    frameHeight = height;
    frameBackground = Color.web(background);
    myFilename = filename;
    myChessController = gameController;
    gridDisplayLength = width - getInt("width_buffer");
    controlPanelX = width - getInt("control_panel_offset");
    myGameViewRoot = new Group();
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

      myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    }
  }

  // creates the scene and initializes all of its components
  private void setupScene() {
    myGameViewRoot = new Group();
    myGameViewScene = new Scene(myGameViewRoot, frameWidth, frameHeight, frameBackground);

    createUIPanels();
    initializeBoundaries();
    myGridPanel = createGrid();
//    myGameViewRoot.getChildren().addAll(myInfoPanel, myDetailsPanel, myAnimationControlPanel, myLoadControlPanel, myViewControlPanel, myGridPanel);
    myGameViewRoot.getChildren().addAll(myInfoPanel, myDetailsPanel, myControlPanel, myGridPanel);

    myGameViewScene.getStylesheets().add(GameView.class.getResource("GameViewFormatting.css").toExternalForm());
  }

  //create the UI panels that will provide interactivity and information to the user
  private void createUIPanels() {
    // Gameplay (left) panel
    myGameplayPanel = createGameplayPanel();

    // Control (right) panel:
    myControlPanel = createControlPanel();
  }



  //create control panel on right of screen to control view, animation/gameplay, and loading/saving
  private Node createControlPanel(){
    ControlPanel newControlPanel = new ControlPanel(controlPanelX, myAnimation);
    newControlPanel.setPanelListener(this);
    return newControlPanel.createControlPanel();
  }

  //create gameplay panel on left of screen to control variant, move history, and dead pieces
  private Node createGameplayPanel(){
    GameplayPanel newGameplayPanel = new GameplayPanel(getInt("x_offset"));
    newGameplayPanel.setPanelListener(this);
    return newGameplayPanel.createGameplayPanel();
  }

  //initialize the grid itself that appears on the scree
  private Node createGrid() {
    gridSize = new int[2];
    gridSize[0]=8;
    gridSize[1]=8;
    myGridView = new GridView(gridSize[0], gridSize[1], myGridColours, gridDisplayLength);
    GridPane myGameGridView = myGridView.getMyGameGrid();
    myGameGridView.setOnMouseClicked(click->updateGrid(click.getX(), click.getY()));
    myGameGridView.setLayoutX(getInt("offset_x") + 3);
    myGameGridView.setLayoutY(getInt("offset_y_top") + 3);
//    myGameController.setupListener(myGridView);
//    try {
//      myGameController.showInitialStates();
//    }
//    catch (ReflectionException e) {
//      sendAlert("InternalError Cannot Make Object");
//    }
    return myGameGridView;
  }

  //create the cosmetic boundaries showing where the simulation takes place
  private void initializeBoundaries() {
    int OFFSET_X = getInt("offset_x");
    int OFFSET_Y_TOP = getInt("offset_y_top");
    Line topLine = new Line(OFFSET_X, OFFSET_Y_TOP, OFFSET_X + gridDisplayLength, OFFSET_Y_TOP);
    topLine.setId("boundary-line");
    Line leftLine = new Line(OFFSET_X, OFFSET_Y_TOP, OFFSET_X, OFFSET_Y_TOP + gridDisplayLength);
    leftLine.setId("boundary-line");
    Line rightLine = new Line(OFFSET_X + gridDisplayLength, OFFSET_Y_TOP,
        OFFSET_X + gridDisplayLength, OFFSET_Y_TOP + gridDisplayLength);
    rightLine.setId("boundary-line");
    Line bottomLine = new Line(OFFSET_X, OFFSET_Y_TOP + gridDisplayLength,
        OFFSET_X + gridDisplayLength, OFFSET_Y_TOP + gridDisplayLength);
    bottomLine.setId("boundary-line");
    myGameViewRoot.getChildren().addAll(topLine, leftLine, rightLine, bottomLine);
  }

  private void updateGrid(double x, double y) {
//    myGameController.calculateIndexesAndUpdateModel(x, y, myGridView.getMyCellHeight(), myGridView.getMyCellWidth());
  }

  //<editor-fold desc="Setup Languages, Conversion, and Update on Change">
  //</editor-fold>

  // runs one step of the simulation
  private void step() {
    //TODO: increment simulation
//    try {
//      myGameController.runSimulation();
//    }
//    catch (ReflectionException e) {
//      sendAlert("InternalError Cannot Make Object");
//    }
  }

  // refreshes the UI panels by removing them from the scene before creating new panels and adding them back
  private void refreshUIPanels(){
    myGameViewRoot.getChildren().removeAll(myInfoPanel, myDetailsPanel, myAnimationControlPanel, myLoadControlPanel, myViewControlPanel);
    createUIPanels();
    myGameViewRoot.getChildren().addAll(myInfoPanel, myDetailsPanel, myAnimationControlPanel, myLoadControlPanel, myViewControlPanel);
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
    //TODO: adjust for this game and callback to Controller to match
//    loadNewFile(myFilename);
    myChessController.resetGame();
  }

  /**
   * Updates the color scheme by simply setting the fill of the background
   * @param newColor desired color scheme
   */
  @Override
  public void updateColorScheme(Color newColor) {
    myGameViewScene.setFill(newColor);
  }

  /**
   * Loads a new file by changing myFilename before resetting the controller and refreshing the grid view/UI panels
   * @param filename name of the file to load
   */
  @Override
  public void loadNewFile(String filename) {
    myFilename = filename;

    myChessController.initializeFromFile(new File(myFilename));
//    setupController();
//    gridSize = myGameController.getGridSize();
//    myGameViewRoot.getChildren().remove(myGridPanel);
//    myGridPanel = createGrid();
//    myGameViewRoot.getChildren().addAll(myGridPanel);
//    myGameController.setupListener(myGridView);
//    try {
//      myGameController.showInitialStates();
//    }
//    catch (ReflectionException e) {
//      sendAlert("InternalError Cannot Make Object");
//    }
//    refreshUIPanels();
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
   * Redo a move that was previously undone
   */
  @Override
  public void redoMove() {
    //TODO: callback to controller
    myChessController.redoMove();
  }

  /**
   * Undo the previous move
   */
  @Override
  public void undoMove() {
    //TODO: callback to controller
    myChessController.undoMove();
  }

  @Override
  public void changeVariant(String variant) {
    //TODO: callback to controller to change the variant
    myChessController.changeVariant(variant);
  }

  //compute which cell on the grid this corresponds to, NOT the pixel position
  //erro check that its' in the board as well
  public void clickedCoordinates(int x, int y){
    //TODO: ensure contronoller callback works
    myChessController.clickedCoordinates(x, y);
  }

  public void updateChessCell(Spot spot){
    myGridView.updateChessCell(spot);
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

}