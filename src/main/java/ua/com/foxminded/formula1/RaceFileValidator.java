package ua.com.foxminded.formula1;

import java.io.File;

public class RaceFileValidator {

    public void validateRaceFiles(String abbreviations, String startTimes, String endTimes){
        try {
            File abbreviationsFile = new File(abbreviations);
            File startTimesFile = new File(startTimes);
            File endTimesFile = new File(endTimes);
            checkFilesOnExists(abbreviationsFile, startTimesFile, endTimesFile);
            checkFilesOnEmpty(abbreviationsFile, startTimesFile, endTimesFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void checkFilesOnEmpty(File abbriveations, File startTimes, File endTimes) {
        if (abbriveations.length() == 0) {
            throw new IllegalArgumentException("Empty abbreviations file!");
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
            throw new IllegalArgumentException("No such abbreviations file!");
        }
        if (!startTimes.exists()) {
            throw new IllegalArgumentException("No such startTimes file!");
        }
        if (!endTimes.exists()) {
            throw new IllegalArgumentException("No such endTimes file!");
        }

    }
}
