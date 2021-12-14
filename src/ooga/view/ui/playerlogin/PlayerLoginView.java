package ooga.view.ui.playerlogin;

import static ooga.util.ResourceRetriever.getWord;
import static ooga.util.ResourceRetriever.showAlert;
import static ooga.util.ResourceRetriever.toHexCode;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.PanelListener;

/**
 * JavaFX panel that creates the player login modal that allows for creation/login of profile
 * Relies on appropriate resourcebundles being configured and JavaFX
 *
 * @author marcusdeans
 */
public class PlayerLoginView extends Application implements PlayerLoginInterface {

  //General resource file structure
  private static final String FORMATTING_FILE = "PlayerLoginFormatting.css";
  private static final String PLAYER_LOGIN_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.PlayerLoginViewResources";
  private static final ResourceBundle playerLoginViewResources = ResourceBundle.getBundle(
      PLAYER_LOGIN_VIEW_RESOURCES_FILE_PATH);
  final String[] teamSelected = new String[1];
  final String[] colorSelected = new String[1];
  PanelListener myPanelListener;
  Stage myStage;
  TextField nameField;
  TextField passwordField;
  int myPlayerIdentifier;

  /**
   * Create the modal allowing hte player to log in
   * @param playerIdentifier the integer identifier of hte player
   */
  public PlayerLoginView(int playerIdentifier) {
    myPlayerIdentifier = playerIdentifier;
  }

  /**
   * Start the JavaFX application
   * @param primaryStage the JavaFX stage upon which the application will be situated
   */
  @Override
  public void start(Stage primaryStage) {
    myStage = primaryStage;
    myStage.setTitle(getWord("login_modal_title_text"));

    // Create the registration form grid pane
    GridPane gridPane = createRegistrationFormPane();
    // Add UI controls to the registration form grid pane
    addUIControls(gridPane);
    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(gridPane, getInt("scene_width"), getInt("scene_height"));

    gridPane.setId("login-pane");
    scene.getStylesheets().add(
        PlayerLoginView.class.getResource(FORMATTING_FILE)
            .toExternalForm());

    // Set the scene in primary stage
    myStage.setScene(scene);

    myStage.show();
  }

  /**
   * Define the PanelListener for the application to communicate with
   * @param panelListener the PanelListener instance
   */
  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  //create the gridpane that will serve as the root of the application
  private GridPane createRegistrationFormPane() {
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

  //add the UI controls and formatting onto the gridpane
  private void addUIControls(GridPane gridPane) {
    Label headerLabel = new Label(getWord("login_title_text"));
    headerLabel.setId("login-header-label");
    gridPane.add(headerLabel, 0, 0, getInt("header_column_span"), getInt("header_row_span"));

    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel,
        new Insets(getInt("gridpaneMargin"), 0, getInt("gridpaneMargin"), 0));

    createLabelsAndTextFields(gridPane);
    createTeamSelector(gridPane);
    createColorPicker(gridPane);
    createSubmitButton(gridPane);
  }

  //create the label and field allowing the user to select the team they want to be on
  private void createTeamSelector(GridPane gridPane) {
    //Add Team Label
    Label teamLabel = makeLabel(getWord("team_label_text"));
    gridPane.add(teamLabel, getInt("label_column"), getInt("team_row"));
    Label teamSelectionLabel = makeLabel(getWord("team_field_error"));
    gridPane.add(teamSelectionLabel, getInt("field_column"), getInt("team_selection_row"));

    //Add Team Field
    ToggleGroup teamRadioGroup = new ToggleGroup();
    RadioButton teamOneRadioButton = new RadioButton(getWord("team_one_name_label"));
    RadioButton teamTwoRadioButton = new RadioButton(getWord("team_two_name_label"));
    teamOneRadioButton.setTextFill(Color.WHITE);
    teamTwoRadioButton.setTextFill(Color.WHITE);
    teamOneRadioButton.setToggleGroup(teamRadioGroup);
    teamTwoRadioButton.setToggleGroup(teamRadioGroup);
    HBox radioButtonBox = new HBox(teamOneRadioButton, teamTwoRadioButton);
    radioButtonBox.setSpacing(getInt("radio_button_spacing"));

    teamRadioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
          Toggle newValue) {
        RadioButton selectedRadioButton = (RadioButton) teamRadioGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
          teamSelected[0] = selectedRadioButton.getText();
          teamSelectionLabel.setText(
              String.format("%s %s", teamSelected[0], getWord("selectedWording")));
        }
      }
    });
    gridPane.add(radioButtonBox, getInt("field_column"), getInt("team_row"));
  }

  //allow the user to select the color that they want their profile to be associated with
  private void createColorPicker(GridPane gridPane) {
    Label colorLabel = makeLabel(getWord("color_label_text"));
    gridPane.add(colorLabel, getInt("label_column"), getInt("colour_row"));
    Label colorSelectionLabel = makeLabel(getWord("color_field_error"));
    gridPane.add(colorSelectionLabel, getInt("field_column"), getInt("color_selection_row"));
    ColorPicker colorPicker = new ColorPicker(Color.web(getString("default_color_selection")));
    colorPicker.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Color selectedColor = colorPicker.getValue();
        colorSelected[0] = toHexCode(selectedColor);
        colorSelectionLabel.setText(
            String.format("%s %s", colorSelected[0], getWord("selectedWording")));
        colorSelectionLabel.setTextFill(selectedColor);
        colorSelectionLabel.setId("color-selection-label");
      }
    });
    gridPane.add(colorPicker, getInt("field_column"), getInt("colour_row"));
  }

  //create the submit button allowing the user to create/login to their profile
  //also performs necesary field validation and error checking
  private void createSubmitButton(GridPane gridPane) {
    // Add Submit Button
    Button submitButton = new Button(getWord("submit_button_text"));
    submitButton.setPrefHeight(getInt("submit_button_height"));
    submitButton.setDefaultButton(true);
    submitButton.setPrefWidth(getInt("submit_button_width"));
    gridPane.add(submitButton, getInt("label_column"), getInt("submit_row"),
        getInt("submit_col_span"), getInt("submit_row_span"));
    GridPane.setHalignment(submitButton, HPos.CENTER);
    GridPane.setMargin(submitButton,
        new Insets(getInt("gridpaneMargin"), 0, getInt("gridpaneMargin"), 0));

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (nameField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR,
              getWord("login_form_error"),
              getWord("name_field_error"));
          return;
        }
        if (passwordField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR,
              getWord("login_form_error"),
              getWord("password_field_error"));
          return;
        }

        String name = nameField.getText();
        String email = getWord("login_form_error");
        String password = passwordField.getText();
        String team = teamSelected[0];
        String colour = colorSelected[0];

        int teamValue;
        switch (team) {
          case "White" -> teamValue = 1;
          case "Black" -> teamValue = 2;
          default -> {
            teamValue = -1;
          }
        }

        try {
          if (myPanelListener.setNewPlayer(myPlayerIdentifier, name, email, password, teamValue,
              colour)) {
            showAlert(Alert.AlertType.CONFIRMATION,
                getWord("login_form_success"), getWord("login_welcome_message") + name);
            myPanelListener.closePlayerLogin(myStage, myPlayerIdentifier);
          } else {
            showAlert(AlertType.ERROR,  getWord("login_form_error"),
                getWord("login_failure_message"));
          }
        } catch (IOException e) {
          showAlert(AlertType.ERROR, getWord("login_form_error"),
              getWord("login_failure_message"));
        }
      }
    });
  }

  //create the labels and text fields for entry by the user
  private void createLabelsAndTextFields(GridPane gridPane) {
    // Add Name Label
    Label nameLabel = makeLabel(getWord("name_label_text"));
    gridPane.add(nameLabel, getInt("label_column"), getInt("name_row"));

    // Add Name Text Field
    nameField = new TextField();
    nameField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(nameField, getInt("field_column"), getInt("name_row"));

    // Add Password Label
    Label passwordLabel = makeLabel(getWord("password_label_text"));
    gridPane.add(passwordLabel, getInt("label_column"), getInt("password_row"));

    // Add Password Field
    passwordField = new PasswordField();
    passwordField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(passwordField, getInt("field_column"), getInt("password_row"));
  }

  //make an individual JavaFX label
  private Label makeLabel(String text) {
    Label newLabel = new Label(text);
    newLabel.setId("field_label");
    return newLabel;
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key) {
    int value;
    try {
      value = Integer.parseInt(playerLoginViewResources.getString(key));
    } catch (Exception e) {
      value = -1;
    }
    return value;
  }

  //retrieves relevant word from the "words" ResourceBundle
  private String getString(String key) {
    String value;
    try {
      value = playerLoginViewResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}