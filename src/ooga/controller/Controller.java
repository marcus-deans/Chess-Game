package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;

import ooga.logic.game.Game;

import ooga.view.View;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Controller {

    //Initializes a new game based on a SIM file input.
    public void initializeFromFile(File file) throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    //Initializes the passed display in this logic controller. @Param - Display
    public void setDisplay(View view);

    //Returns the stage of the active display.
    public Stage getStage();

    //handles what to do with pieces when clicked to move
    public void clickState(int row, int column);

    //gets the current game class
    public Game getCurrentGame();

    //Resets the active game and the display.
    public void resetGame();

    //sets time limit of each round in the game
    public void setTime(int speed);

}
