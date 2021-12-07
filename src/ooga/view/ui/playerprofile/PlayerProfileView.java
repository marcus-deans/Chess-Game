package ooga.view.ui.playerprofile;

import static ooga.util.ResourceRetriever.getWord;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.logic.game.Player;
import ooga.view.PanelListener;
import ooga.view.ui.playerlogin.PlayerLoginView;

public class PlayerProfileView extends Application implements PlayerProfileInterface{
  PanelListener myPanelListener;
  Player myPlayer;
  Stage myStage;
  private static final String FORMATTING_FILE = "PlayerLoginFormatting.css";
  private static final String PLAYER_PROFILE_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.PlayerProfileViewResources";
  private static final ResourceBundle playerProfileViewResources = ResourceBundle.getBundle(
      PLAYER_PROFILE_VIEW_RESOURCES_FILE_PATH);

  public PlayerProfileView(Player player){
    myPlayer = player;
  }

  /**
   * The main entry point for all JavaFX applications. The start method is called after the init
   * method has returned, and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which the application scene
   *                     can be set. Applications may create other stages, if needed, but they will
   *                     not be primary stages.
   */
  @Override
  public void start(Stage primaryStage)   {
    myStage = primaryStage;
    myStage.setTitle(getWord("login_modal_title_text"));

    // Create the registration form grid pane
//    GridPane gridPane = createRegistrationFormPane();
    // Add UI controls to the registration form grid pane
//    addUIControls(gridPane);
    GridPane gridPane = new GridPane();
    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(gridPane, 700, 500);

//    gridPane.setId("login-pane");
    scene.getStylesheets().add(
        PlayerLoginView.class.getResource(FORMATTING_FILE)
            .toExternalForm());

    // Set the scene in primary stage
    myStage.setScene(scene);

    myStage.show();

    //    public String getUserScore() throws IOException {
    // wins, losses



    try {
//      String score = currentPlayer.getUserScore();
      myPlayer.getUsername();
      myPlayer.getTeam();
      myPlayer.getProfileColor();
      System.out.println(myPlayer.getUserScore());
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key) {
    int value;
    try {
      value = Integer.parseInt(playerProfileViewResources.getString(key));
    } catch (Exception e) {
      value = -1;
    }
    return value;
  }

  //retrieves relevant word from the "words" ResourceBundle
  private String getString(String key) {
    String value;
    try {
      value = playerProfileViewResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}
