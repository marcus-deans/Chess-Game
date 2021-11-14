package ooga.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SIMParser {
    /**
     * Reads in the SIM file and parses the data
     * @param file the SIM file being read
     * @return Map of the property name followed by the String representation with that name
     * @throws IOException
     */
    public Map<String, String> readSimFile(File file) throws IOException {
        Map<String, String> data = new HashMap<>();
        Properties properties = simIntoProperties(file);
        for(String property : properties.stringPropertyNames()) {
            data.put(property, properties.getProperty(property));
        }
        return data;
    }

    /**
     * Converts the SIM file into a Properties File for reading into the Map of property names and values
     * @param file the SIM file to be converted into a Properties file
     * @return the Properties file converted from the SIM file
     * @throws IOException
     */
    private Properties simIntoProperties(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
}
