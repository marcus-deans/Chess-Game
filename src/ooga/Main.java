package ooga;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.controller.ChessController;
import ooga.controller.Controller;
import ooga.util.ResourceRetriever;

/**
 * Main driving class for the application that provides an entry point for the user
 * Relies on appropriate resourcebundles being configured and JavaFX
 *
 * @author marcusdeans
 */
public class Main extends Application {

  private static final String MAIN_WORDS_PATH = "ooga.view.viewresources.MainResources";
  private static final ResourceBundle gameTitleWords = ResourceBundle.getBundle(MAIN_WORDS_PATH);

  /**
   * Organize display of game in a scene and start the game.
   */
  @Override
  public void start(Stage stage) {
    stage.setScene(setupDisplay());
    stage.setTitle(getString("menu_title"));
    stage.show();
  }

  //setup the JavaFX Scene to hold all content as well as importing appropriate styling
  private Scene setupDisplay() {
    Scene myMenuScene = new Scene(setupMenuRoot(), getInt("menuWidth"), getInt("menuHeight"));
    myMenuScene.getStylesheets()
        .add(Main.class.getResource(getString("stylingFile")).toExternalForm());
    return myMenuScene;
  }


  //create a JavaFX VBox to contain all of the elements present in the venu
  private VBox setupMenuRoot() {
    VBox myMenuRoot = new VBox();

    Label programTitle = makeTitleLabel(getString("welcomeText"));
    HBox myMenuButton = makeMenuButtonPanel();

    myMenuRoot.getChildren().addAll(programTitle, myMenuButton);
    myMenuRoot.setAlignment(Pos.CENTER);
    myMenuRoot.setSpacing(getInt("menuVerticalSpacing"));
    return myMenuRoot;
  }

  //create JavaFX Horizontal panel containing buttons
  private HBox makeMenuButtonPanel() {
    HBox menuButtonPanel = new HBox();

    Button newGameButton = makeButton(getString("newGameButtonText"), value -> {
      try {
        startNewGame("Standard.sim");
      } catch (IOException e) {
        ResourceRetriever.showAlert(AlertType.ERROR,
            ResourceRetriever.getWord("main_error"),
            ResourceRetriever.getWord("main_error_message"));
      }
    });

    menuButtonPanel.getChildren().addAll(newGameButton);
    menuButtonPanel.setAlignment(Pos.CENTER);
    menuButtonPanel.setSpacing(getInt("menuHorizontalSpacing"));
    return menuButtonPanel;
  }

  //create a JavaFX Button with the appropriate text as well as provided EventHandler
  private Button makeButton(String property, EventHandler<ActionEvent> response) {
    Button gameSelectionButton = new Button();
    gameSelectionButton.setId("start-game-button");
    gameSelectionButton.setText(property);
    gameSelectionButton.setPrefWidth(getInt("buttonWidth"));
    gameSelectionButton.setPrefHeight(getInt("buttonHeight"));
    gameSelectionButton.setOnAction(response);
    return gameSelectionButton;
  }

  //create a new game animation based on the default app file provided
  private void startNewGame(String appFileName) throws IOException {
    Controller newGame = new ChessController(getInt("frameWidth"), getInt("frameHeight"),
        getString("backgroundColor"), appFileName);
//        newGameView.start(new Stage());
  }

  //create a JavaFX Label for the menu title
  private Label makeTitleLabel(String words) {
    Label newLabel = new Label(words);
    newLabel.setId("main-title-label");
    return newLabel;
  }

  //return the String  from the resource file based on the provided string
  private String getString(String key) {
    String value;
    try {
      value = gameTitleWords.getString(key);
    } catch (Exception e) {
      value = "Null";
    }
    return value;
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key) {
    int value;
    try {
      value = Integer.parseInt(gameTitleWords.getString(key));
    } catch (Exception e) {
      value = -1;
    }
    return value;
  }

  //allow the user to select a file to load the game from
  private File makeFileChooser(String description, String extensions) {
    FileChooser myFileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description,
        extensions);
    myFileChooser.getExtensionFilters().add(extFilter);
    File selectedFile = myFileChooser.showOpenDialog(null);
    return selectedFile;
  }
}
