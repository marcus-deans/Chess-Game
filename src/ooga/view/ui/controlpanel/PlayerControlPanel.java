package ooga.view.ui.controlpanel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ooga.view.ui.SharedUIComponents;

public class PlayerControlPanel extends SharedUIComponents {
  public PlayerControlPanel(){

  }

  public Node createPlayerControlPanel(){
    VBox playerControlPanel = new VBox();
    playerControlPanel.setSpacing(getInt("control_subpanel_spacing"));

    Node clearScreenButton = initializePlayerLoginButton();
    playerControlPanel.getChildren().add(clearScreenButton);

    playerControlPanel.setAlignment(Pos.CENTER);
//    panel.setLayoutX(myControlPanelX);
//    panel.setLayoutY(getInt("animation_control_panel_y"));
    playerControlPanel.setId("animation-control-panel");

    return playerControlPanel;
  }

  private Node initializePlayerLoginButton(){
    return new VBox();
  }
}
