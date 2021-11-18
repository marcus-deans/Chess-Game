package ooga.controller;

import javafx.stage.Stage;

import ooga.logic.game.Game;

import ooga.view.View;

import java.io.File;

public interface Controller {

    //Initializes a new game based on a SIM file input.
    public void initializeFromFile(File file);

    //Initializes the passed display in this logic controller. @Param - Display
    public void setDisplay(View view);

    //Returns the stage of the active display.
    public Stage getStage();

  Game getCurrentGame();

  //Resets the active game and the display.
    public void resetGame();

    //sets time limit of each round in the game
    public void setTime(int speed);

    public void undoMove();

    public void redoMove();

    public void changeVariant(String variant);

}
