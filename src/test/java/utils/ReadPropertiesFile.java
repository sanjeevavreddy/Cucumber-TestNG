package utils;

import java.io.*;
import java.util.*;

public class ReadPropertiesFile {

    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
    public String returnPropertyValue(String repoElementName) throws IOException {
        Properties prop = readPropertiesFile(TestConstants.ELEMENT_FILE_PATH);
        return prop.getProperty(repoElementName);
    }
}