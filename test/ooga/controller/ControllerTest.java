package ooga.controller;

import com.opencsv.exceptions.CsvValidationException;
import ooga.Parser.CSVParser;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    private ChessController myController;
    private Map<String, String> myData;

    /**
     * Here are three test cases for initializing the board
     */
    @Test
    public void setDataTest() throws CsvValidationException, IOException, IncorrectCSVFormatException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File file = new File("data/Standard.sim");
        myController = new ChessController(8,8,"Blue","data/Standard.sim");
        myController.initializeFromFile(file);
        assertEquals(8 ,myController.getHeight());

    }
    @Test
    public void initializeFromFileTest() throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File file = new File("data/Standard.sim");
        myController.initializeFromFile(file);
    }
//    @Test
//    public void initializeBoardTestBlackHole(){
//        // read CSV file for StarWars
//        // assertEquals(X, getState(5,4));
//    }
//
//    @Test
//    public void CSVParserSizeTest(){
//        // read CSV file for Standard
//        // getDimensions()
//        // assertEquals(8, getNumRows())
//    }
//    @Test
//    public void SIMParserTypeTest(){
//        // read SIM file for standard
//        // assertEquals(Standard, getType());
//    }
//    @Test
//    public void SIMParserBHTest(){
//        // read CSV file for StarWars
//        // assertTrue(myData.getValue(BlackHole));
//    }
}

