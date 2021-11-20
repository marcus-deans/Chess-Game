package ooga.view.ui.gameplaypanel;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.view.ui.SharedUIComponents;

public class GraveyardPanel extends SharedUIComponents {
  private VBox myGraveyardContent;

  public GraveyardPanel(){
    myGraveyardContent = new VBox();
  }

  public Node createGraveyardPanel(){
    VBox graveyardPanel = new VBox();
    return graveyardPanel;
  }

  /**
   * Create the details panel that displays colour legend and parameters of the simulation
   *
   * @return the JavaFX HBox that constitutes the details panel
   */
  public Node createHistoryPanel() {
    VBox myHistoryPanel = new VBox();
    myHistoryPanel.setSpacing(getInt("gameplay_subpanel_spacing"));
    myHistoryPanel.setId("graveyard-panel");

    Text graveyardPanelTitle = makeText(getWord("graveyard_panel_title"));
    ScrollPane graveyardScrollPane = makeGraveyardScrollPane();
    myHistoryPanel.getChildren().addAll(graveyardPanelTitle, graveyardScrollPane);

    myHistoryPanel.setVgrow(graveyardScrollPane, Priority.ALWAYS);

    return myHistoryPanel;
  }

  private ScrollPane makeGraveyardScrollPane(){
    ScrollPane newScrollPane = new ScrollPane();
    newScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    newScrollPane.fitToWidthProperty().set(true);

    newScrollPane.setVmax(getInt("max_graveyard_scrollpane_height"));
    newScrollPane.setPrefSize(getInt("pref_graveyard_scrollpane_width"), getInt("pref_graveyard_scrollpane_height"));
    newScrollPane.setContent(myGraveyardContent);

//    sp.vvalueProperty().addListener((ObservableValue<? extends Number> ov,
//        Number old_val, Number new_val) -> {
//      fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
//    });

    return newScrollPane;
  }

  public void addHistory(String action){
    Text newGraveyardEntry = makeText(action);
    myGraveyardContent.getChildren().add(newGraveyardEntry);
  }

}
