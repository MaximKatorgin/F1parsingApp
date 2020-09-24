package ua.com.foxminded.formula1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class RaceFileReader {

    public List<String> readFileToList(String filePath) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            Stream<String> abbriveationsStream = Files.lines(new File(filePath).toPath());
            abbriveationsStream.forEach(fileData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileData;
    }
}
