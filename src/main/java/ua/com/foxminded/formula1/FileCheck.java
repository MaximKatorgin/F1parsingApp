package ua.com.foxminded.formula1;

import java.io.File;

public class FileCheck {

    public void checkFiles(File abbriveations, File startTimes, File endTimes){
        checkFilesOnExists(abbriveations, startTimes, endTimes);
        checkFilesOnEmpty(abbriveations, startTimes, endTimes);
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
}
