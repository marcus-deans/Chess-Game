package ooga.Parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ooga.util.IncorrectCSVFormatException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {

    private int [] gridDimensions;
    private String[][] boardSetUp;

    /**
     * Reads in the CSV file and parses the data to fit the Grid implementation
     * @param file CSV file to be read
     * @throws IOException
     * @throws CsvValidationException
     */
    public void readCSVFile(File file) throws IOException, CsvValidationException, IncorrectCSVFormatException {
        FileReader filereader = new FileReader(file.getPath());
        CSVReader csvReader = new CSVReader(filereader);
        setDimensions(csvReader.readNext());
        writeInitialStates(csvReader);
    }

    //Takes the rows and columns inputted from the file and initializes those values
    private void setDimensions(String [] dimensions) throws IncorrectCSVFormatException {
        try {
            gridDimensions = new int[]{Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[0])};
        }
        catch (NullPointerException e){
            throw new IncorrectCSVFormatException("Dimensions are not set");
        }
    }

    //Takes each element in the CSV file and puts it into a 2D grid
    private void writeInitialStates(CSVReader csvReader) throws IOException, CsvValidationException {
        boardSetUp = new String[gridDimensions[0]][gridDimensions[1]];
        for(int i = 0; i < gridDimensions[0]; i++) {
            String [] nextRecord = csvReader.readNext();
            for(int j = 0; j < gridDimensions[1]; j++) {
                boardSetUp[i][j] = String.valueOf(nextRecord[j]);
            }
        }
    }

    /**
     * Gets the rows and columns for the created Grid
     * @return the rows and columns for the Grid
     */
    public int [] getDimensions() {
        return gridDimensions;
    }

    /**
     * Gets the initial states of the Grid
     * @return the initial states of the Grid
     */
    public String[][] getInitialStates() {
        return boardSetUp;
    }
}

