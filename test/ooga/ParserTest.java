package ooga;

import com.opencsv.exceptions.CsvValidationException;
import ooga.Parser.CSVParser;
import ooga.Parser.SIMParser;
import ooga.util.IncorrectCSVFormatException;
import ooga.util.IncorrectSimFormatException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    //private Controller myController;
    private Map<String, String> myData;

    /**
     * Test that ensure the dimensions of the board are determined properly from the file
     * @throws CsvValidationException
     * @throws IOException
     */
    @Test
    public void readCSVFileTestDimensions() throws CsvValidationException, IOException, IncorrectCSVFormatException {
        CSVParser csvReader = new CSVParser();
        File file= new File("data/Standard.csv");
        csvReader.readCSVFile(file);
        assertEquals(8,csvReader.getDimensions()[0]);
        assertEquals(8,csvReader.getDimensions()[1]);
    }

    /**
     * Test that checks that all four corners of the board contain rooks for default use.
     * @throws CsvValidationException
     * @throws IOException
     */
    @Test
    public void readCSVFileTestRooks() throws CsvValidationException, IOException, IncorrectCSVFormatException {
        CSVParser csvReader = new CSVParser();
        File file= new File("data/Standard.csv");
        csvReader.readCSVFile(file);
        assertEquals("R1",csvReader.getInitialStates()[0][0]);
        assertEquals("R2",csvReader.getInitialStates()[7][7]);
        assertEquals("R2",csvReader.getInitialStates()[7][0]);
        assertEquals("R1",csvReader.getInitialStates()[0][7]);
    }

    /**
     * Test that uses the Standard sim file to create a map and check the type
     * @throws IOException
     */
    @Test
    public void readSIMFileTestStandard() throws IOException{
        SIMParser simReader = new SIMParser();
        File file= new File("data/Standard.sim");
        Map<String, String> mydata = simReader.readSimFile(file);
        assertEquals("Standard", mydata.get("Type"));
    }

    /**
     * Test that uses the StarWars sim file to create a map and check the type
     * @throws IOException
     */
    @Test
    public void readSIMFileTestStarWars() throws IOException{
        SIMParser simReader = new SIMParser();
        File file= new File("data/StarWars.sim");
        Map<String, String> mydata = simReader.readSimFile(file);
        assertEquals("StarWars", mydata.get("Type"));
    }

    /**
     * Test to see whether exception is thrown for no csv file existing
     */
    @Test
    public void readCSVFileTestNoFile() {
        CSVParser csvReader = new CSVParser();
        File badFile = new File("data/noRealFile.csv");
        assertThrows(IOException.class, () -> csvReader.readCSVFile(badFile));
    }

    /**
     * Test to see whether exception is thrown for no sim file existing
     * @throws IOException
     */
    @Test
    public void readSIMFileTestNoFile(){
        SIMParser simReader = new SIMParser();
        File badFile = new File("data/noRealFile.sim");
        assertThrows(IOException.class, () -> simReader.readSimFile(badFile));
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void readSIMFileTestBadMap() throws IOException {
        SIMParser simReader = new SIMParser();
        File file= new File("data/BadFile.sim");
        Map<String, String> data = simReader.readSimFile(file);
        assertEquals(null, data.get("Type"));
    }
    @Test
    public void readSIMFileTestBadCSV(){
        CSVParser csvReader = new CSVParser();
        File csvfile = new File("data/BadFile.csv");
        assertThrows(IncorrectCSVFormatException.class, () -> csvReader.readCSVFile(csvfile));
    }
}
