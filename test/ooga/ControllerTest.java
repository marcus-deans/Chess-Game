package ooga;

import ooga.Parser.CSVParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ControllerTest {
    //private Controller myController;
    private Map<String, String> myData;

    /**
     * Here are three test cases for initializing the board
     */
    @Test
    public void initializeBoardTestRooks(){
        // read CSV file for standard
        // assertEquals(R, getState(0,0));
        // assertEquals(R, getState(0,7));
        // assertEquals(R, getState(0,7));
        // assertEquals(R, getState(7,0));
    }
    @Test
    public void initializeBoardTestKings(){
        // read CSV file for standard
        // assertEquals(K, getState(0,4));
        // assertEquals(K, getState(7,4));
    }
    @Test
    public void initializeBoardTestBlackHole(){
        // read CSV file for StarWars
        // assertEquals(X, getState(5,4));
    }

    @Test
    public void CSVParserSizeTest(){
        // read CSV file for Standard
        // getDimensions()
        // assertEquals(8, getNumRows())
    }
    @Test
    public void SIMParserTypeTest(){
        // read SIM file for standard
        // assertEquals(Standard, getType());
    }
    @Test
    public void SIMParserBHTest(){
        // read CSV file for StarWars
        // assertTrue(myData.getValue(BlackHole));
    }
}

