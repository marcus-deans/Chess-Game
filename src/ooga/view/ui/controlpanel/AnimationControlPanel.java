package ooga.view.ui.controlpanel;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * JavaFX panel that creates the animation control panel for start/clear/stop/run/step simulation
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public class AnimationControlPanel extends ControlPanel {
  private Button pauseGameButton;
  private Label mySpeedLabel;
  private boolean isPaused;
  private Timeline myAnimation;

  /**
   * Initialize the animation contorl panel creator
   * @param animation the JavaFX animation that should be affected by the buttons
   * @param controlPanelX the location on-screen of the control panel
   */
  public AnimationControlPanel(Timeline animation, int controlPanelX){
    super(controlPanelX);
    myAnimation = animation;
    createAnimationControlPanel();
  }

  /**
   * Create the animation control panel that allows the user to control speed/progress of animation
   * @return the JavaFX HBox that constitutes the animation control panel
   */
  public Node createAnimationControlPanel(){
    VBox panel = new VBox();
    panel.setSpacing(getInt("control_panel_spacing"));

    Node runGameButton = initializeRunAnimationButton();
    panel.getChildren().add(runGameButton);

    Node pauseGameButton = initializePauseButton();
    panel.getChildren().add(pauseGameButton);

    Node stepAnimationButton = initializeStepAnimationButton();
    panel.getChildren().add(stepAnimationButton);

    Node clearScreenButton = initializeResetGameButton();
    panel.getChildren().add(clearScreenButton);

    mySpeedLabel = makeInformationLabel(String.format("%s: %s", getWord("rate_word"),
        myAnimation.getRate()));
    panel.getChildren().add(mySpeedLabel);

    panel.setAlignment(Pos.CENTER);
    panel.setLayoutX(myControlPanelX);
    panel.setLayoutY(getInt("animation_control_panel_y"));
    panel.setId("animation-control-panel");

    return panel;
  }

  //create button to run simulation (playing myAnimation continuously calls myGameController.runSimulation)
  private Node initializeRunAnimationButton() {
    Button runAnimationButton = makeButton(getWord("run_game"), value -> {
      if(isPaused){
        pauseGameButton.setText(getWord("pause_game"));
        isPaused = false;
      }
      myAnimation.play();
    });
    return runAnimationButton;
  }

  //start and stop button in UI
  private Node initializePauseButton() {
    pauseGameButton = makeButton(getWord("pause_game"), value -> togglePause());
    return pauseGameButton;
  }

  // Start or stop searching animation as appropriate
  private void togglePause() {
    if (isPaused) {
      pauseGameButton.setText(getWord("pause_game"));
      myAnimation.play();
    } else {
      pauseGameButton.setText(getWord("resume_game"));
      myAnimation.pause();
    }
    isPaused = !isPaused;
  }

  //create button to step through animation
  private Node initializeStepAnimationButton() {
    Button stepAnimationButton = makeButton(getWord("step_game"), value -> {
//      try {
////        myGameController.runSimulation();
//          int fixme = 2+2;
//      } catch (ReflectionException e) {
//        sendAlert("InternalError Cannot Make Object");
//      }
    });
    return stepAnimationButton;
  }

  //create the clear screen button
  private Node initializeResetGameButton() {
    Button resetGameButton = makeButton(getWord("clear_text"), event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().resetGame();
      }
    });
    return resetGameButton;
  }

}
