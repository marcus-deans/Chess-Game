package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.scene.control.Labeled;
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

public class ControllerTest extends Application {
    private ChessController myController;
    private Map<String, String> myData;
    private File myFile;
    private Labeled myLabel;
    private Stage myStage;
    private Main myMain;


    protected static final String TITLE = "GameView";
    public static final int FRAME_WIDTH = 733;
    public static final int FRAME_HEIGHT = 680;
    public static final Paint BACKGROUND = Color.web("#00539B");


    @BeforeEach
    public void setUpController() throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myFile = new File("data/Standard.sim");

        myController = new ChessController(8,8,"Blue","data/Standard.sim");
        myController.initializeFromFile(myFile);
    }

    /**
     * Here are three test cases for initializing the board
     */
    @Test
    public void setDataTest() throws CsvValidationException, IOException, IncorrectCSVFormatException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertEquals(8 ,myController.getHeight());

    }
    @Test
    public void initializeFromFileTest() throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController.initializeFromFile(myFile);
    }
//    @Test
//    public void initializeBoardTestBlackHole(){
//        // read CSV file for StarWars
//        // assertEquals(X00, getState(5,4));
//    }
//

    @Test
    public void getDimensions(){
        assertEquals(myController.getHeight(), myController.getWidth());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

