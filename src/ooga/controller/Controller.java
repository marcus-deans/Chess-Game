package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;

import ooga.logic.game.Game;

import ooga.logic.game.Player;
import ooga.util.IncorrectCSVFormatException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Controller {

    //Initializes a new game based on a SIM file input.
    public void initializeFromFile(File file) throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException;

  //Resets the active game and the display.
    public void resetGame();

    public int getHeight();

    public int getWidth();
    //sets time limit of each round in the game
    public void setTime(int speed);

    public void undoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    public void redoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    public void changeVariant(String variant);

    public void clickedCoordinates(int x, int y) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    @Deprecated
    public void setPlayer(String userName, int team) throws IOException;

    public boolean setPlayer(int playerIdentifier, String userName, String password, int team, String color) throws IOException;

    public void acceptCheatCode(String identifier);

    public Player getPlayer(int playerIdentifier);
  }
