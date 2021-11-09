package ooga.controller;

import javafx.stage.Stage;
import ooga.logic.board.Piece;
import ooga.logic.game.Game;

import java.io.File;

public interface Controller {

    //Initializes a new game based on a SIM file input.
    public void initializeFromFile(File file);

    //Initializes the passed display in this logic controller. @Param - Display
    public void setDisplay();

    //Returns the stage of the active display.
    public Stage getStage();

    //gets the current game class
    public Game getCurrentGame();

    //Resets the active game and the display.
    public void resetGame();

    //sets time limit of each round in the game
    public void setTime(int speed);

}
