package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Parser.CSVParser;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import ooga.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest extends DukeApplicationTest {
    private ChessController myController;
    private File myFile;

    @BeforeEach
    public void setUpController() throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myFile = new File("data/Standard/Standard.sim");
        runAsJFXAction(() -> {
            try {
                myController = new ChessController(8,8,"Blue","data/Standard/Standard.sim");
            } catch (IOException e) {
            }
        });
        runAsJFXAction(() ->{
            try {
                myController.initializeFromFile(myFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (IncorrectCSVFormatException e) {
            } catch (CsvValidationException e) {
            }
        });
    }

    /**
     * Here are three test cases for initializing the board
     */
    @Test
    public void setDataTest() throws CsvValidationException, IOException, IncorrectCSVFormatException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertEquals(8 ,myController.getHeight());
    }

    @Test
    public void getDimensions(){
        assertEquals(myController.getHeight(), myController.getWidth());
    }

    @Test
    public void movePawnTest() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        //Button setPlayer = lookup("Player Profile").query();
        myController.setPlayer(1, "Carter", "1", 1, "White");
        runAsJFXAction(() -> {
            try {
                myController.clickedCoordinates(1, 6);
            } catch (ClassNotFoundException e) {

            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
            try {
                myController.clickedCoordinates(1, 4);
            } catch (ClassNotFoundException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        });
        //assertTrue();

    }
    @Test
    public void samePieceTest() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        //Button setPlayer = lookup("Player Profile").query();
        myController.setPlayer(1, "Carter", "1", 1, "White");
        runAsJFXAction(() -> {
            try {
                myController.clickedCoordinates(1, 6);
            } catch (ClassNotFoundException e) {

            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
            try {
                myController.clickedCoordinates(1, 6);
            } catch (ClassNotFoundException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        });
    }
    @Test
    public void InvalidPieceTest() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        //Button setPlayer = lookup("Player Profile").query();
        myController.setPlayer(1, "Carter", "1", 1, "White");
        runAsJFXAction(() -> {
            try {
                myController.clickedCoordinates(1, 6);
            } catch (ClassNotFoundException e) {

            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
            try {
                myController.clickedCoordinates(2, 5);
            } catch (ClassNotFoundException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        });
    }
}

