package ooga.controller;

import java.io.File;
import javafx.stage.Stage;
import ooga.logic.game.Game;
import ooga.view.GameView;
import ooga.view.View;

public class GameController implements Controller {
  private GameView myGameView;

  public GameController(int width, int height, String background, String filename){
    myGameView = new GameView(width, height, background, filename, this);
    myGameView.start(new Stage());
  }

  public GameController(int width, int height, String background){
    new GameController(width, height, background, "default");
  }

  @Override
  public void initializeFromFile(File file) {

  }

  @Override
  public void setDisplay(View view) {

  }

  @Override
  public Stage getStage() {
    return null;
  }

  @Override
  public Game getCurrentGame() {
    return null;
  }

  @Override
  public void resetGame() {

  }

  @Override
  public void setTime(int speed) {

  }
}
