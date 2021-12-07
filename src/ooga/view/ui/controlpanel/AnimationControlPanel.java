package ooga.view.ui.controlpanel;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.view.ui.SharedUIComponents;

import java.lang.reflect.InvocationTargetException;

/**
 * JavaFX panel that creates the animation control panel for start/clear/stop/run/step simulation
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class AnimationControlPanel extends SharedUIComponents {
  private Button pauseGameButton;
  private Label mySpeedLabel;
  private boolean isPaused;

  /**
   * Initialize the animation contorl panel creator
   */
  public AnimationControlPanel(){
    createAnimationControlPanel();
  }

  /**
   * Create the animation control panel that allows the user to control speed/progress of animation
   * @return the JavaFX HBox that constitutes the animation control panel
   */
  public Node createAnimationControlPanel(){
    VBox panel = new VBox();
    panel.setSpacing(getInt("control_subpanel_spacing"));

    Node clearScreenButton = initializeResetGameButton();
    panel.getChildren().add(clearScreenButton);

    Node undoButton = initializeUndoButton();
    panel.getChildren().add(undoButton);

    Node redoButton = initializeRedoButton();
    panel.getChildren().add(redoButton);

    panel.setAlignment(Pos.CENTER);
    panel.setId("animation-control-panel");

    return panel;
  }

  //create button to undo one user action
  private Node initializeUndoButton() {
    Button undoButton = makeButton(getWord("undo_move"), value -> {
      //TODO: callback to controller to undo
      if(this.getPanelListener() != null){
        try {
          this.getPanelListener().undoMove();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
          sendAlert(getWord("reflection_error"));
        }
      }
    });
    undoButton.setId("undo-button");
    return undoButton;
  }

  //start and stop button in UI
  private Node initializeRedoButton() {
    Button redoButton = makeButton(getWord("redo_move"), value -> {
      //TODO: callback to controller to redo
      if(this.getPanelListener() != null){
        try {
          this.getPanelListener().redoMove();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
          sendAlert(getWord("reflection_error"));
        }
      }
    });
    redoButton.setId("redo-button");
    return redoButton;
  }

  //create the clear screen button
  private Node initializeResetGameButton() {
    return makeButton(getWord("clear_text"), event -> {
      if(this.getPanelListener() != null){
        this.getPanelListener().resetGame();
      }
    });
  }

}
