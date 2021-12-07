package ooga.view.ui.gameplaypanel;

import java.util.Iterator;
import java.util.LinkedList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the details panel that displays game colours and parameters Relies on
 * appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class HistoryPanel extends SharedUIComponents {

  private VBox myHistoryContent;
  private LinkedList<Label> myHistoryList; //want LinkedList method not present in List interface
  private ScrollPane myHistoryScrollpane;

  /**
   * Initialize the details panel creator
   */
  public HistoryPanel() {
    myHistoryContent = new VBox();
    myHistoryList = new LinkedList<>();
    myHistoryScrollpane = new ScrollPane();
  }

  /**
   * Create the details panel that displays colour legend and parameters of the simulation
   *
   * @return the JavaFX HBox that constitutes the details panel
   */
  public Node createHistoryPanel() {
    VBox myHistoryPanel = new VBox();
    myHistoryPanel.setSpacing(getInt("gameplay_subpanel_spacing"));
    myHistoryPanel.setAlignment(Pos.CENTER);
    myHistoryPanel.setId("history-panel");

    StackPane historyPanelTitle = makePanelTitle(getWord("history_panel_title"),
        getInt("pref_history_scrollpane_width"));
    historyPanelTitle.setId("history-panel-title");
    myHistoryScrollpane = makeHistoryScrollPane();
    myHistoryPanel.getChildren().addAll(historyPanelTitle, myHistoryScrollpane);

    VBox.setVgrow(myHistoryScrollpane, Priority.ALWAYS);
    return myHistoryPanel;
  }

  /**
   * Create the ScrollPane containing all the history of the game
   * @return the JavaFX ScrollPane
   */
  private ScrollPane makeHistoryScrollPane() {
    ScrollPane newScrollPane = new ScrollPane();
    newScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    newScrollPane.fitToWidthProperty().set(true);

    newScrollPane.setVmax(getInt("max_history_scrollpane_height"));
    newScrollPane.setPrefSize(getInt("pref_history_scrollpane_width"),
        getInt("pref_history_scrollpane_height"));
    newScrollPane.setContent(myHistoryContent);

//    sp.vvalueProperty().addListener((ObservableValue<? extends Number> ov,
//        Number old_val, Number new_val) -> {
//      fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
//    });

    return newScrollPane;
  }

  /**
   * Add a single element to the history
   * @param action the string that will be added
   */
  public void addHistory(String action) {
    myHistoryList.add(makeHistoryLabel(action));
    rebuildHistoryDisplay();
  }

  /**
   * Make the text for a specific history element
   * @param text the history to be labeled
   * @return the JavaFX label
   */
  private Label makeHistoryLabel(String text) {
    Label newLabel = new Label(text);
    newLabel.setId("history-label");
    return newLabel;
  }

  //rebuild the history display to reflect all changes
  private void rebuildHistoryDisplay() {
    Iterator<Label> it = myHistoryList.iterator();
    myHistoryContent = new VBox();
    while (it.hasNext()) {
      myHistoryContent.getChildren().add(it.next());
    }
    myHistoryScrollpane.setContent(myHistoryContent);
  }

  /**
   * Remove the last element from the history
   */
  public void removeHistory() {
    myHistoryList.removeLast();
    rebuildHistoryDisplay();
  }

}
