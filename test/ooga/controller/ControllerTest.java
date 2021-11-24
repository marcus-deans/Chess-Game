package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import ooga.Parser.CSVParser;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    private ChessController myController;
    private Map<String, String> myData;
    private File myFile;

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

