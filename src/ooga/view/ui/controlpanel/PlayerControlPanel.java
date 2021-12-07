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
  List<Button> myPlayerButtons;
  int myPlayerButtonIndex;

  public PlayerControlPanel(){
    myPlayerList = new ArrayList<>();
    myPlayerButtons = new ArrayList<>();
    myPlayerButtonIndex = 0;
  }

  public Node createPlayerControlPanel(){
    VBox playerControlPanel = new VBox();
    playerControlPanel.setSpacing(getInt("control_subpanel_spacing"));

    Node playerLoginButton = initializePlayerLoginButton();
    playerControlPanel.getChildren().add(playerLoginButton);

    playerControlPanel.setAlignment(Pos.CENTER);
    playerControlPanel.setId("player-control-panel");
    return playerControlPanel;
  }

  private Node initializePlayerLoginButton(){
    Button playerLoginButton = makeButton(getWord("player_login_button_prompt"), event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().openPlayerLogin(myPlayerButtonIndex);
      }
    });
    myPlayerButtons.add(playerLoginButton);
    myPlayerButtonIndex++;
    return playerLoginButton;
  }

  public void playerHasLoggedIn(int playerIndex){
    try {
      Button loggedInPlayerButton = myPlayerButtons.get(playerIndex-1);
      loggedInPlayerButton.setText(getWord("player_login_button_display"));
      loggedInPlayerButton.setOnAction(action -> this.getPanelListener().openPlayerProfile(playerIndex));
    } catch (Exception e){
      System.out.println(playerIndex);
      sendAlert(getWord("player_display_error"));
    }
  }
}
