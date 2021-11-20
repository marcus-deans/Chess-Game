package ooga.view.ui.gameplaypanel;

import static java.util.Map.entry;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the details panel that displays game colours and parameters Relies on
 * appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public class HistoryPanel extends SharedUIComponents {

  private VBox myHistoryContent;


  /**
   * Initialize the details panel creator
   *
   */
  public HistoryPanel() {
    myHistoryContent = new VBox();
  }

  /**
   * Create the details panel that displays colour legend and parameters of the simulation
   *
   * @return the JavaFX HBox that constitutes the details panel
   */
  public Node createHistoryPanel() {
    VBox myHistoryPanel = new VBox();
    myHistoryPanel.setSpacing(getInt("gameplay_subpanel_spacing"));
    myHistoryPanel.setId("history-panel");

    Text historyPanelTitle = makeText(getWord("history_panel_title"));
    ScrollPane historyScrollPane = makeHistoryScrollPane();
    myHistoryPanel.getChildren().addAll(historyPanelTitle, historyScrollPane);

    myHistoryPanel.setVgrow(historyScrollPane, Priority.ALWAYS);

    return myHistoryPanel;
  }

  private ScrollPane makeHistoryScrollPane(){
    ScrollPane newScrollPane = new ScrollPane();
    newScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    newScrollPane.fitToWidthProperty().set(true);

    newScrollPane.setVmax(getInt("max_history_scrollpane_height"));
    newScrollPane.setPrefSize(getInt("pref_history_scrollpane_width"), getInt("pref_history_scrollpane_height"));
    newScrollPane.setContent(myHistoryContent);

//    sp.vvalueProperty().addListener((ObservableValue<? extends Number> ov,
//        Number old_val, Number new_val) -> {
//      fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
//    });

    return newScrollPane;
  }

  public void addHistory(String action){
    Text newHistoryEntry = makeText(action);
    myHistoryContent.getChildren().add(newHistoryEntry);
  }

  public void removeHistory(){
    //TODO: implement way to remove content without hbeing able gto address it
  }

}
