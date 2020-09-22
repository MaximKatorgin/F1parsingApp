package ua.com.foxminded.formula1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;


public class RaceFileReader {
    private RaceFileValidator raceFileValidator = new RaceFileValidator();

    public ArrayList<String> getAbbriveationsData(String abbriveationsPath) {
        ArrayList<String> abbriveationsData = new ArrayList<>();
        try {
            Stream<String> abbriveationsStream = Files.lines(new File(abbriveationsPath).toPath());
            abbriveationsStream.forEach(abbriveationsData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return abbriveationsData;
    }

    public ArrayList<String> getStartTimesData(String startTimesPath) {
        ArrayList<String> startTimesData = new ArrayList<>();
        try {
            Stream<String> startTimesStream = Files.lines(new File(startTimesPath).toPath());
            startTimesStream.forEach(startTimesData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return startTimesData;
    }

    public ArrayList<String> getEndTimesData(String endTimesPath) {
        ArrayList<String> endTimesData = new ArrayList<>();
        try {
            Stream<String> endTimesStream = Files.lines(new File(endTimesPath).toPath());
            endTimesStream.forEach(endTimesData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return endTimesData;
    }

}
