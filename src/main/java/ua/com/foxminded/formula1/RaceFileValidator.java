package ua.com.foxminded.formula1;

import java.io.File;

public class RaceFileValidator {

    public void validateRaceFile(String filePath){
            try {
                checkFilePath(filePath);
                File raceDataFile = new File(filePath);
                checkFileOnExist(raceDataFile);
                checkIsNotDirectory(raceDataFile);
                checkIsReadable(raceDataFile);
                checkFileOnEmpty(raceDataFile);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    private void checkFilePath(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("Null file path");
        }
        if (filePath == "") {
            throw new IllegalArgumentException("Null file is empty");
        }
    }

    private void checkIsReadable(File raceDataFile) {
        if (!raceDataFile.canRead()) {
            throw new IllegalArgumentException("File " + raceDataFile.getName() + " is not readable!");
        }
    }

    private void checkIsNotDirectory(File raceDataFile) {
        if (raceDataFile.isDirectory()) {
            throw new IllegalArgumentException("File " + raceDataFile.getName() + " is directory!");
        }
    }

    private void checkFileOnEmpty(File raceDataFile) {
        if (raceDataFile.length() == 0) {
            throw new IllegalArgumentException("Empty file " + raceDataFile.getName() + "!");
        }
    }

    private void checkFileOnExist(File raceDataFile) {
        if (!raceDataFile.exists()) {
            throw new IllegalArgumentException("No such file " + raceDataFile.getName() + "!");
        }
    }
}
