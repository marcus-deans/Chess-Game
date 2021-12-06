package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.control.Labeled;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
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
    private Map<String, String> myData;
    private File myFile;
    private Labeled myLabel;
    private Stage myStage;


    protected static final String TITLE = "GameView";
    public static final int FRAME_WIDTH = 733;
    public static final int FRAME_HEIGHT = 680;
    public static final Paint BACKGROUND = Color.web("#00539B");

    @Override
    public void start (Stage stage) {
        // create application and add scene for testing to given stage
        GameView myGameView = new GameView(FRAME_WIDTH , FRAME_HEIGHT,8,8, "Blue", "data/Standard.sim", myController);
        myGameView.start(stage);
        stage.setTitle(TITLE);
        stage.show();

        myGameView.start(stage);

        // components that will be reused in different tests
        myLabel = lookup("#Label").query();
    }

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
}

