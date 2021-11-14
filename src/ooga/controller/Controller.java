package ooga.controller;

import javafx.stage.Stage;
<<<<<<< HEAD
import ooga.logic.game.Game;
=======
>>>>>>> 202e0f29021a5c83cef6c9ee6a3bd68cbf536d39
import ooga.view.View;

import java.io.File;

public interface Controller {

    //Initializes a new game based on a SIM file input.
    public void initializeFromFile(File file);

    //Initializes the passed display in this logic controller. @Param - Display
    public void setDisplay(View view);

    //Returns the stage of the active display.
    public Stage getStage();

    //gets the current game class
    public Game getCurrentGame();

    //Resets the active game and the display.
    public void resetGame();

    //sets time limit of each round in the game
    public void setTime(int speed);

}
