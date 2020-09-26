package ua.com.foxminded.formula1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class RaceReportBuilder {
    private static final char MINS_AND_MILLIS_DELIMITER = '.';
    private static final char HOURS_AND_MINS_DELIMITER = ':';
    private static final String INFO_PART_DELIMITER = " |";
    private  ArrayList<Racer> racersList;

    public RaceReportBuilder(ArrayList<Racer> racersList) {
        racersList.sort(Comparator.comparing(Racer::getLapTime));
        this.racersList = racersList;
    }
    public ArrayList<Racer> getReportInList() {
        return this.racersList;
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
