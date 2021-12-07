package ooga.view.ui.controlpanel;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ooga.view.PanelListener;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX superclass that holds the necessary shared information for the control panel displays.
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class ControlPanel extends SharedUIComponents {
  private int myControlPanelX;
  private Timeline myAnimation;
  private PlayerControlPanel myPlayerControlPanel;

  /**
   * Create the general control panel constructor
   * @param controlPanelX the location on the UI that the control panel should be located at
   */
  public ControlPanel(int controlPanelX, Timeline animation){
    myControlPanelX = controlPanelX;
    myAnimation = animation;
  }

  /**
   * Create the control panel itself with all relevant buton
   * @return the completed JavaFX control panel
   */
  public Node createControlPanel(){
    VBox newControlPanel = new VBox();
    newControlPanel.setSpacing(getInt("control_panel_spacing"));
    newControlPanel.setAlignment(Pos.CENTER);
    newControlPanel.setLayoutX(myControlPanelX);
    newControlPanel.setLayoutY(getInt("control_panel_y"));

    ViewControlPanel myViewControlPanel = new ViewControlPanel();
    myViewControlPanel.setPanelListener(this.getPanelListener());
    newControlPanel.getChildren().add(myViewControlPanel.createViewControlPanel());

    AnimationControlPanel myAnimationControlPanel = new AnimationControlPanel();
    myAnimationControlPanel.setPanelListener(this.getPanelListener());
    newControlPanel.getChildren().add(myAnimationControlPanel.createAnimationControlPanel());

    LoadControlPanel myLoadControlPanel = new LoadControlPanel(myAnimation);
    myLoadControlPanel.setPanelListener(this.getPanelListener());
    newControlPanel.getChildren().add(myLoadControlPanel.createLoadControlPanel());

    myPlayerControlPanel = new PlayerControlPanel();
    myPlayerControlPanel.setPanelListener(this.getPanelListener());
    newControlPanel.getChildren().add(myPlayerControlPanel.createPlayerControlPanel());

    return newControlPanel;
  }

  /**
   * When playerh as logged in, must adjust control panel
   * @param playerIndex the index of the player who has screwed in
   */
  public void playerHasLoggedIn(int playerIndex){
    myPlayerControlPanel.playerHasLoggedIn(playerIndex);
  }
}
