package ua.com.foxminded.formula1;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Racer> timesList = new ArrayList<>();
        try {
            File abbreviations = new File(Main.class.getResource("/abbreviations.txt").getPath());
            File startTimes = new File(Main.class.getResource("/start.log").getPath());
            File endTimes = new File(Main.class.getResource("/end.log").getPath());
            LapTimeReader lapTimeReader = new LapTimeReader(abbreviations, startTimes, endTimes);
            timesList = lapTimeReader.getQualificationTimes();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        LapTimeReport lapTimeReport = new LapTimeReport();
        lapTimeReport.getReport(timesList);

    }
}
