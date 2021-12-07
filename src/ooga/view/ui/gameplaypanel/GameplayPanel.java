package ooga.view.ui.gameplaypanel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.view.ui.SharedUIComponents;

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

  public void updateHistory(String historyText) {
    myHistoryPanel.addHistory(historyText);
  }

  public void removeHistory() {
    myHistoryPanel.removeHistory();
  }

  public void updateGraveyard(Piece deadPiece) {
    myGraveyardPanel.addGraveyardEntry(deadPiece);
  }

  public void setBoardDescription(String description){
    myVariantPanel.setBoardDescription(description);
  }
}
