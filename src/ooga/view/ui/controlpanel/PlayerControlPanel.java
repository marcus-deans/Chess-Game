package ooga.view.ui.controlpanel;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ooga.logic.game.Player;
import ooga.view.ui.SharedUIComponents;

public class PlayerControlPanel extends SharedUIComponents {
  List<Player> myPlayerList;

  public PlayerControlPanel(){
    myPlayerList = new ArrayList<>();
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
    Button playerLoginButton = new Button();
    playerLoginButton.setText(getWord("player_login_button_prompt"));
    playerLoginButton.setOnAction(event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().openPlayerLogin();
      }
      playerLoginButton.setText(getWord("player_login_button_display"));
      playerLoginButton.setOnAction(action -> this.getPanelListener().openPlayerProfile());
    });
    return playerLoginButton;
  }

  private void newPlayerCreated(){

  }
}
