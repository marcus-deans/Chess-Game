package ooga.view.ui.controlpanel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    playerControlPanel.setId("player-control-panel");
    return playerControlPanel;
  }

  private Node initializePlayerLoginButton(){
    Button playerLoginButton = makeButton(getWord("player_login_button"), event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().openPlayerLogin();
      }
    });
    return playerLoginButton;
  }
}
