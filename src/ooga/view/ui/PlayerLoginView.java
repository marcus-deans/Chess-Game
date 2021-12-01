package ooga.view.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;
import ooga.view.PanelListener;

public class PlayerLoginView extends Application implements PlayerLoginInterface {

  PanelListener myPanelListener;


  public PlayerLoginView() {

  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registration Form JavaFX Application");

    // Create the registration form grid pane
    GridPane gridPane = createRegistrationFormPane();
    // Add UI controls to the registration form grid pane
    addUIControls(gridPane);
    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(gridPane, 800, 500);

    scene.getStylesheets().add(
        PlayerLoginView.class.getResource("PlayerLoginFormatting.css")
            .toExternalForm());

    // Set the scene in primary stage
    primaryStage.setScene(scene);

    primaryStage.show();
  }

  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  private GridPane createRegistrationFormPane() {
    // Instantiate a new Grid Pane
    GridPane gridPane = new GridPane();

    // Position the pane at the center of the screen, both vertically and horizontally
    gridPane.setAlignment(Pos.CENTER);

    // Set a padding of 20px on each side
    gridPane.setPadding(new Insets(40, 40, 40, 40));

    // Set the horizontal gap between columns
    gridPane.setHgap(10);

    // Set the vertical gap between rows
    gridPane.setVgap(10);

    // Add Column Constraints

    // columnOneConstraints will be applied to all the nodes placed in column one.
    ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    columnOneConstraints.setHalignment(HPos.RIGHT);

    // columnTwoConstraints will be applied to all the nodes placed in column two.
    ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
    columnTwoConstrains.setHgrow(Priority.ALWAYS);

    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

    return gridPane;
  }

  private void addUIControls(GridPane gridPane) {
    // Add Header
    Label headerLabel = new Label("Registration Form");
    headerLabel.setId("login-header-label");
    gridPane.add(headerLabel, 0, 0, 2, 1);

    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

    // Add Name Label
    Label nameLabel = new Label("Full Name : ");
    gridPane.add(nameLabel, 0, 1);

    // Add Name Text Field
    TextField nameField = new TextField();
    nameField.setPrefHeight(40);
    gridPane.add(nameField, 1, 1);

    // Add Email Label
    Label emailLabel = new Label("Email ID : ");
    gridPane.add(emailLabel, 0, 2);

    // Add Email Text Field
    TextField emailField = new TextField();
    emailField.setPrefHeight(40);
    gridPane.add(emailField, 1, 2);

    // Add Password Label
    Label passwordLabel = new Label("Password : ");
    gridPane.add(passwordLabel, 0, 3);

    // Add Password Field
    PasswordField passwordField = new PasswordField();
    passwordField.setPrefHeight(40);
    gridPane.add(passwordField, 1, 3);

    //Add Team Label
    Label teamLabel = new Label("Team : ");
    gridPane.add(teamLabel, 0, 4);

    //Add Team Field
    TextField teamField = new TextField();
    teamField.setPrefHeight(40);
    gridPane.add(teamField, 1, 4);

    // Add Submit Button
    Button submitButton = new Button("Submit");
    submitButton.setPrefHeight(40);
    submitButton.setDefaultButton(true);
    submitButton.setPrefWidth(100);
    gridPane.add(submitButton, 0, 5, 2, 1);
    GridPane.setHalignment(submitButton, HPos.CENTER);
    GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (nameField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
              "Please enter your name");
          return;
        }
        if (emailField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
              "Please enter your email id");
          return;
        }
        if (passwordField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
              "Please enter a password");
          return;
        }
        if (teamField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
              "Please enter a team");
        }

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        int team = Integer.parseInt(teamField.getText());

        showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
            "Registration Successful!", "Welcome " + name);
        myPanelListener.setNewPlayer(name, email, password, team);
      }
    });
  }

  private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
  }
}
