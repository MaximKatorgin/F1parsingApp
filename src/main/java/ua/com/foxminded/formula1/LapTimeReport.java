package ua.com.foxminded.formula1;

import java.time.Duration;
import java.util.ArrayList;

public class LapTimeReport {
    private static final char MINS_AND_MILLIS_DELIMITER = '.';
    private static final char HOURS_AND_MINS_DELIMITER = ':';
    private static final String INFO_PART_DELIMITER = " |";
    private  ArrayList<Racer> racersList = new ArrayList<>();

    public LapTimeReport(ArrayList<Racer> racersList) {
        this.racersList = racersList;
    }

    public void printReport() {
        for (int i = 0; i < racersList.size(); i++) {
            System.out.println(i + 1 + ". " + getRacerLapTimeInfo(racersList.get(i)));
            if (i == 14) {
                System.out.println("------------------------------------------");
            }
        }
    }

    private String getRacerLapTimeInfo(Racer racer) {
        return new StringBuilder().append(racer.getName())
                .append(INFO_PART_DELIMITER)
                .append(racer.getTeam())
                .append(INFO_PART_DELIMITER)
                .append(formatLapTime(racer.getLapTime()))
                .toString();
    }

    private String formatLapTime(Duration lapTime) {
        StringBuilder stringLapTime = new StringBuilder();
        stringLapTime.append(lapTime.toMinutesPart())
                .append(HOURS_AND_MINS_DELIMITER)
                .append(lapTime.toSecondsPart())
                .append(MINS_AND_MILLIS_DELIMITER)
                .append(lapTime.toMillis());
        return stringLapTime.toString();
    }
}
