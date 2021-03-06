package ooga.view.ui;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.view.PanelListener;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ooga.view.PanelListener;

/**
 * JavaFX superclass that facilitates the creation of the individual panels and buttons on the
 * screen and includes all shared resources and methods. Relies on appropriate resourcebundles being
 * configured as well as JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public abstract class SharedUIComponents {

  //General resource file structure
  private static final String UI_FILE_PATH = "ooga.view.viewresources.GameViewResources";
  private static final ResourceBundle uiLocationResources = ResourceBundle.getBundle(UI_FILE_PATH);

  private PanelListener myPanelListener;

  /**
   * Sets the listener object that will be notified/called upon whenever the state of a UI panel changes due to user interaction
   * @param panelListener the PanelListener instance
   */
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  /**
   * Returns the PanelListener, allowing UI panel subclasses to interact with the listener
   * @return the PanelListener
   */
  protected PanelListener getPanelListener(){
    return myPanelListener;
  }

  //<editor-fold desc="Create General JavaFX Element Creators">
  //method to create individual text label
  protected Text makeText(String text) {
    Text newText = new Text(text);
    newText.setId("information-text");
    return newText;
  }

  //method to create title text for panel
  protected StackPane makePanelTitle(String text, int width) {
    StackPane panelTitle = new StackPane();
    panelTitle.setId("panel-title");
    Rectangle colorBox = new Rectangle();
    colorBox.setId("panel-title-colour-box");
    colorBox.setWidth(width);
    colorBox.setHeight(getInt("button_height"));
    Text panelTitleText = makeText(text);
    panelTitleText.setId("panel-title-text");
    panelTitle.getChildren().addAll(colorBox, panelTitleText);
    return panelTitle;
  }

  //create a JavaFX Button with the appropriate text as well as provided EventHandler
  protected Button makeButton(String property, EventHandler<ActionEvent> response) {
    Button newButton = new Button();
    newButton.setText(property);
    newButton.setPrefWidth(getInt("button_width"));
    newButton.setPrefHeight(getInt("button_height"));
    newButton.setOnAction(response);
    return newButton;
  }

  //create a JavaFX ComboBox (dropdown) with the appropriate title and provided options and Eventhandler
  protected ComboBox makeComboBox(String title, List<String> boxOptions,
      EventHandler<ActionEvent> response) {
    ComboBox newComboBox = new ComboBox<>(FXCollections.observableList(boxOptions));
    newComboBox.setPrefWidth(getInt("button_width"));
    newComboBox.setPrefHeight(getInt("button_height"));
    newComboBox.setPromptText(title);
    newComboBox.setOnAction(response);
    return newComboBox;
  }

  //create a JavaFX HBox to serve as an individual panel consisting of text and label
  protected HBox makeHorizontalPanel(Text text, Label label) {
    HBox newHorizontalPanel = new HBox();
    newHorizontalPanel.setSpacing(getInt("horizontal_panel_spacing"));
    newHorizontalPanel.getChildren().addAll(text, label);
    return newHorizontalPanel;
  }
  //</editor-fold>

  //get a String resource from the resource file
  protected String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = "error";
    try {
      value = words.getString(key);
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File %s", key, UI_FILE_PATH));
    }
    return value;
  }

  //get an Integer resource from the resource file
  protected int getInt(String key) {
    int value = -1;
    try {
      value = Integer.parseInt(uiLocationResources.getString(key));
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File %s", key, UI_FILE_PATH));
    }
    return value;
  }

  //get a Double resource from the resource file
  protected double getDouble(String key) {
    double value = -1.0;
    try {
      value = Double.parseDouble(uiLocationResources.getString(key));
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File %s", key, UI_FILE_PATH));
    }
    return value;
  }

  //set an alert to the user indicating incorrect input
  protected void sendAlert(String alertMessage) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(alertMessage);
    alert.show();
  }
}
