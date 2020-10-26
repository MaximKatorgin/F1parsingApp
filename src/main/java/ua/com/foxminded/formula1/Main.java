package ua.com.foxminded.formula1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RaceFileParser raceFileParser = new RaceFileParser();
        List<Racer> racerList = new ArrayList<>();
        try {
            racerList = raceFileParser.parseRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                                        Main.class.getResource("/start.log").getPath(),
                                        Main.class.getResource("/end.log").getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        RaceReportBuilder raceReportBuilder = new RaceReportBuilder(racerList);
        raceReportBuilder.printReport();
    }
}
