package ooga.view.ui.controlpanel;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ooga.logic.game.Player;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the player control panel for defining which players are going
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class PlayerControlPanel extends SharedUIComponents {
  List<Player> myPlayerList;
  List<Button> myPlayerButtons;
  int myPlayerButtonIndex;

  /**
   * Create player control panel
   */
  public PlayerControlPanel(){
    myPlayerList = new ArrayList<>();
    myPlayerButtons = new ArrayList<>();
    myPlayerButtonIndex = 0;
  }

  /**
   * Build the javaFX player control panel
   * @return completed control panel
   */
  public Node createPlayerControlPanel(){
    VBox playerControlPanel = new VBox();
    playerControlPanel.setSpacing(getInt("control_subpanel_spacing"));

    Node playerLoginAlphaButton = initializePlayerLoginButton();
    playerControlPanel.getChildren().add(playerLoginAlphaButton);

    Node playerLoginBravoButton = initializePlayerLoginButton();
    playerControlPanel.getChildren().add(playerLoginBravoButton);

    playerControlPanel.setAlignment(Pos.CENTER);
    playerControlPanel.setId("player-control-panel");
    return playerControlPanel;
  }

  //create a single button that will allow the player to log in
  private Node initializePlayerLoginButton(){
    Button playerLoginButton = makeButton(String.format("%s %d %s", getWord("player"), myPlayerButtonIndex+1, getWord("login")), event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().openPlayerLogin(myPlayerButtonIndex);
      }
    });
    myPlayerButtons.add(playerLoginButton);
    myPlayerButtonIndex++;
    return playerLoginButton;
  }

  /**
   * When player logs in, adjust button accordingly to reflect new profile
   * @param playerIndex the index of the player, used to keep track of buttons
   */
  public void playerHasLoggedIn(int playerIndex){
    try {
      Button loggedInPlayerButton = myPlayerButtons.get(playerIndex-1);
      loggedInPlayerButton.setText(String.format("%s %d %s", getWord("player"), myPlayerButtonIndex+1, getWord("profile")));
      loggedInPlayerButton.setOnAction(action -> this.getPanelListener().openPlayerProfile(playerIndex-1));
    } catch (Exception e){
      System.out.println(playerIndex);
      sendAlert(getWord("player_display_error"));
    }
  }
}
