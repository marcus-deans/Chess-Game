package ooga;

import com.opencsv.exceptions.CsvValidationException;
import ooga.Parser.CSVParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    //private Controller myController;
    private Map<String, String> myData;

    /**
     * Test that ensure the dimensions of the board are determined properly from the file
     * @throws CsvValidationException
     * @throws IOException
     */
    @Test
    public void readCSVFileTestDimensions() throws CsvValidationException, IOException {
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
    public void readCSVFileTestRooks() throws CsvValidationException, IOException {
        CSVParser csvReader = new CSVParser();
        File file= new File("data/Standard.csv");
        csvReader.readCSVFile(file);
        assertEquals("R",csvReader.getInitialStates()[0][0]);
        assertEquals("R",csvReader.getInitialStates()[7][7]);
        assertEquals("R",csvReader.getInitialStates()[7][0]);
        assertEquals("R",csvReader.getInitialStates()[7][0]);
    }
}
