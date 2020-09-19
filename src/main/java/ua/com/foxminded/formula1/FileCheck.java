package ua.com.foxminded.formula1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileCheck {
    private final String abbriveationsPath;
    private final String startTimesPath;
    private final String endTimesPath;

    public FileCheck(String abbriveations, String startTimes, String endTimes) {
            this.abbriveationsPath = abbriveations;
            this.startTimesPath = startTimes;
            this.endTimesPath = endTimes;
    }

    public void checkFiles(){
        try {
            File abbriveationsFile = new File(abbriveationsPath);
            File startTimesFile = new File(startTimesPath);
            File endTimesFile = new File(endTimesPath);
            checkFilesOnExists(abbriveationsFile, startTimesFile, endTimesFile);
            checkFilesOnEmpty(abbriveationsFile, startTimesFile, endTimesFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void checkFilesOnEmpty(File abbriveations, File startTimes, File endTimes) {
        if (abbriveations.length() == 0) {
            throw new IllegalArgumentException("Empty abbriveations file!");
        }
        if (startTimes.length() == 0) {
            throw new IllegalArgumentException("Empty startTimes file!");
        }
        if (endTimes.length() == 0) {
            throw new IllegalArgumentException("Empty endTimes file!");
        }
    }

    private void checkFilesOnExists(File abbriveations, File startTimes, File endTimes) {
        if (!abbriveations.exists()) {
            throw new IllegalArgumentException("No such abbriveations file!");
        }
        if (!startTimes.exists()) {
            throw new IllegalArgumentException("No such startTimes file!");
        }
        if (!endTimes.exists()) {
            throw new IllegalArgumentException("No such endTimes file!");
        }

    }

    public ArrayList<String> getAbbriveationsData() {
        ArrayList<String> abbriveationsData = new ArrayList<>();
        try {
            Stream<String> abbriveationsStream = Files.lines(new File(abbriveationsPath).toPath());
            abbriveationsStream.forEach(abbriveationsData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return abbriveationsData;
    }

    public ArrayList<String> getStartTimesData() {
        ArrayList<String> startTimesData = new ArrayList<>();
        try {
            Stream<String> startTimesStream = Files.lines(new File(startTimesPath).toPath());
            startTimesStream.forEach(startTimesData::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return startTimesData;
    }

    public ArrayList<String> getEndTimesData() {
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
