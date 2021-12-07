package ooga.view.ui.gameplaypanel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the greaveyard panel for the dead pieces
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class GraveyardPanel extends SharedUIComponents {
  private VBox myGraveyardContent;

  public GraveyardPanel(){
    myGraveyardContent = new VBox();
  }

  /**
   * Create the details panel that displays colour legend and parameters of the simulation
   *
   * @return the JavaFX HBox that constitutes the details panel
   */
  public Node createGraveyardPanel() {
    VBox myGraveyardPanel = new VBox();
    myGraveyardPanel.setSpacing(getInt("gameplay_subpanel_spacing"));
    myGraveyardPanel.setId("graveyard-panel");
    myGraveyardPanel.setAlignment(Pos.CENTER);

    StackPane graveyardPanelTitle = makePanelTitle(getWord("graveyard_panel_title"), getInt("pref_graveyard_scrollpane_width"));
    graveyardPanelTitle.setId("graveyard-panel-title");
    ScrollPane graveyardScrollPane = makeGraveyardScrollPane();
    myGraveyardPanel.getChildren().addAll(graveyardPanelTitle, graveyardScrollPane);

    myGraveyardPanel.setVgrow(graveyardScrollPane, Priority.ALWAYS);

    return myGraveyardPanel;
  }

  /**
   * Create the scrollpane that will contain everything inside the graveyard
   * @return the JavaFX ScvrollPane
   */
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

  /**
   * Add an element to the graveyeard
   * @param deadPiece the pieces that is dead
   */
  public void addGraveyardEntry(Piece deadPiece){
    String action = "dyanmically get piece image";
    Text newGraveyardEntry = makeText(action);
    myGraveyardContent.getChildren().add(newGraveyardEntry);
  }

}
