package ooga.view.ui.playerprofile;

import static ooga.util.ResourceRetriever.getWord;
import static ooga.util.ResourceRetriever.showAlert;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.logic.game.Player;
import ooga.view.PanelListener;
import ooga.view.ui.playerlogin.PlayerLoginView;

/**
 * JavaFX panel that creates the player profile modal that allows the user to see their profile details
 * Relies on appropriate resourcebundles being configured and JavaFX
 *
 * @author marcusdeans
 */
public class PlayerProfileView extends Application implements PlayerProfileInterface {

  private static final String FORMATTING_FILE = "PlayerProfileFormatting.css";
  private static final String PLAYER_PROFILE_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.PlayerProfileViewResources";
  private static final ResourceBundle playerProfileViewResources = ResourceBundle.getBundle(
      PLAYER_PROFILE_VIEW_RESOURCES_FILE_PATH);
  PanelListener myPanelListener;
  Player myPlayer;
  Stage myStage;
  GridPane myGridPane;

  /**
   * Create a new player profile modal
   * @param player the player that the modal will describe
   */
  public PlayerProfileView(Player player) {
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
  public void start(Stage primaryStage) {
    myStage = primaryStage;
    myStage.setTitle(getWord("login_modal_title_text"));

    myGridPane = createPlayerProfilePane();
    // Add UI controls to the registration form grid pane
    addUI();
    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(myGridPane, getInt("scene_width"), getInt("scene_height"));
    myGridPane.setId("profile-pane");
    scene.getStylesheets().add(PlayerProfileView.class.getResource(FORMATTING_FILE).toExternalForm());

    // Set the scene in primary stage
    myStage.setScene(scene);

    myStage.show();
  }

  //create the GridPane that will serve as the root of the application
  private GridPane createPlayerProfilePane() {
    GridPane gridPane = new GridPane();

    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(
        new Insets(getInt("gridpanePadding"), getInt("gridpanePadding"), getInt("gridpanePadding"),
            getInt("gridpanePadding")));
    gridPane.setHgap(getInt("login_grid_pane_hgap"));
    gridPane.setVgap(getInt("login_grid_pane_vgap"));

    ColumnConstraints columnOneConstraints = new ColumnConstraints(getInt("column_one_width"),
        getInt("column_one_width"), Double.MAX_VALUE);
    columnOneConstraints.setHalignment(HPos.RIGHT);
    ColumnConstraints columnTwoConstrains = new ColumnConstraints(getInt("column_two_width"),
        getInt("column_two_width"), Double.MAX_VALUE);
    columnTwoConstrains.setHgrow(Priority.ALWAYS);

    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

    return gridPane;
  }

  //add the standard UI for the player profile modal
  private void addUI() {
    Label headerLabel = new Label(getWord("login_title_text"));
    headerLabel.setId("login-header-label");
    myGridPane.add(headerLabel, 0, 0, getInt("header_column_span"), getInt("header_row_span"));

    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel,
        new Insets(getInt("gridpaneMargin"), 0, getInt("gridpaneMargin"), 0));

    Label nameLabel = makeLabel(getWord("name_label_text"));
    myGridPane.add(nameLabel, getInt("label_column"), getInt("name_row"));
    Label teamLabel = makeLabel(getWord("team_label_text"));
    myGridPane.add(teamLabel, getInt("label_column"), getInt("team_row"));

    addPlayerDetailsToUI();
    //    public String getUserScore() throws IOException {
    // wins, losses
  }

  //determine the olayer's specific details from theo bject
  private void addPlayerDetailsToUI(){
    Label nameField = new Label();
    Label teamField  = new Label();
    Color playerColor = Color.web(getString("default_color_selection"));
    String[] playerScore = new String[2];
    try{
      nameField = makeLabel(myPlayer.getUsername());
      teamField = makeLabel(determineTeam(myPlayer.getTeam()));
      playerColor = Color.web("#" + myPlayer.getProfileColor());
      playerScore = myPlayer.getUserScore().split(",");
    } catch (Exception e){
      showAlert(AlertType.ERROR, getWord("player_profile_error_title"), getWord("player_profile_error_message"));
    }
    Label winField = makeLabel(String.format("%s: %s", getWord("number_wins"),playerScore[0]));
    Label loseField = makeLabel(String.format("%s: %s", getWord("number_loses"), playerScore[1]));

    myGridPane.add(nameField, getInt("field_column"), getInt("name_row"));
    myGridPane.add(teamField, getInt("field_column"), getInt("team_row"));
    myGridPane.add(winField, getInt("label_column"), getInt("score_row"));
    myGridPane.add(loseField, getInt("field_column"), getInt("score_row"));

    myGridPane.setBackground(new Background(new BackgroundFill(playerColor, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  //determine which team the player is on based on the field
  private String determineTeam(int teamNumber){
    Map<Integer, String> intMap = Map.of(1, "Black", 2, "White");
    return intMap.getOrDefault(teamNumber, "Error");
  }

  /**
   * Define the PanelListener that allows for conectivity of this modal
   * @param panelListener the PanelListener instance
   */
  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  //Make an individual JavaFX label
  private Label makeLabel(String text) {
    Label newLabel = new Label(text);
    newLabel.setId("field_label");
    return newLabel;
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
