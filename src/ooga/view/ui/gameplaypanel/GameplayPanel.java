package ooga.view.ui.gameplaypanel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the gameplay control panel for direct gameplay features like history and variants
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class GameplayPanel extends SharedUIComponents {

  private int myGameplayPanelX;
  private GraveyardPanel myGraveyardPanel;
  private HistoryPanel myHistoryPanel;
  private VariantPanel myVariantPanel;
  private String myDescription;

  /**
   * Create the general control panel constructor
   *
   * @param gameplayPanelX the location on the UI that the control panel should be located at
   */
  public GameplayPanel(int gameplayPanelX, String description) {
    myGameplayPanelX = gameplayPanelX;
    myDescription = description;
  }

  /**
   * Create the gameplay panel with all necessary components
   * @return the JavaFX gameplay panel
   */
  public Node createGameplayPanel() {
    VBox newGameplayPanel = new VBox();
    newGameplayPanel.setSpacing(getInt("gameplay_panel_spacing"));
    newGameplayPanel.setAlignment(Pos.CENTER);
    newGameplayPanel.setLayoutX(myGameplayPanelX);
    newGameplayPanel.setLayoutY(getInt("gameplay_panel_y"));

    myVariantPanel = new VariantPanel(myDescription);
    myVariantPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myVariantPanel.createVariantPanel());

    myHistoryPanel = new HistoryPanel();
    myHistoryPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myHistoryPanel.createHistoryPanel());

    myGraveyardPanel = new GraveyardPanel();
    myGraveyardPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myGraveyardPanel.createGraveyardPanel());

    return newGameplayPanel;
  }

  /**
   * Add an element to the history
   * @param historyText text to be aded
   */
  public void updateHistory(String historyText) {
    myHistoryPanel.addHistory(historyText);
  }

  /**
   * Remove the last element from the history
   */
  public void removeHistory() {
    myHistoryPanel.removeHistory();
  }

  /**
   * Add a piece to the graveyard of dead pieces
   * @param deadPiece the piece that has been eliminated
   */
  public void updateGraveyard(Piece deadPiece) {
    myGraveyardPanel.addGraveyardEntry(deadPiece);
  }

  /**
   * Set the description of the current simulation
   * @param description the string description of the simulatiobn
   */
  public void setBoardDescription(String description){
    myVariantPanel.setBoardDescription(description);
  }
}
