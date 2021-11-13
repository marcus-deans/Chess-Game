package ooga.view.ui.gameplaypanel;

import static java.util.Map.entry;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the details panel that displays game colours and parameters Relies on
 * appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public class HistoryPanel extends SharedUIComponents {

  private final Map<String, String[]> colourLabelNames = Map.ofEntries(
      entry("GameOfLife",
          new String[]{getWord("gameoflife_cellstate1"), getWord("gameoflife_cellstate2")}),
      entry("SpreadingOfFire", new String[]{getWord("fire_cellstate1"), getWord("fire_cellstate2"),
          getWord("fire_cellstate3")}),
      entry("Segregation", new String[]{getWord("segregation_cellstate1"), getWord("segregation_cellstate2"),
          getWord("segregation_cellstate3")}),
      entry("WatorWorld", new String[]{getWord("wator_cellstate1"), getWord("wator_cellstate2"),
          getWord("wator_cellstate3")}),
      entry("Percolation", new String[]{getWord("percolation_cellstate1"), getWord("percolation_cellstate2"),
          getWord("percolation_cellstate3")})
  );


  /**
   * Initialize the details panel creator
   *
   */
  public HistoryPanel() {
    createHistoryPanel();
  }

  /**
   * Create the details panel that displays colour legend and parameters of the simulation
   *
   * @return the JavaFX HBox that constitutes the details panel
   */
  public Node createHistoryPanel() {
    HBox myDetailsPanel = new HBox();
    myDetailsPanel.setSpacing(getInt("gameplay_subpanel_spacing"));

    myDetailsPanel.setId("details-panel");

    return myDetailsPanel;
  }

}
