package ua.com.foxminded.formula1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Racer {
    private String name;
    private String team;
    private String abbriveation;
    private LocalDateTime lapStartTime;
    private LocalDateTime lapEndTime;
    private Duration lapTime;
    private String formattedLapTime;
    private static final char MINS_AND_MILLIS_DELIMITER = '.';
    private static final char HOURS_AND_MINS_DELIMITER = ':';
    private static final String INFO_PART_DELIMITER = " |";

    public Racer(String name, String team, String abbriveation) {
        this.name = name;
        this.team = team;
        this.abbriveation = abbriveation;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    public String getAbbriveation() {
        return abbriveation;
    }

    public void setLapTime(LocalDateTime lapStartTime, LocalDateTime lapEndTime) {
        this.lapStartTime = lapStartTime;
        this.lapEndTime = lapEndTime;
        this.lapTime = Duration.between(lapStartTime, lapEndTime);
        this.formattedLapTime = this.getPrettyLapTime(lapTime);
    }

    private String getPrettyLapTime(Duration lapTime) {
        StringBuilder stringLapTime = new StringBuilder();
        stringLapTime.append(lapTime.toMinutesPart());
        stringLapTime.append(HOURS_AND_MINS_DELIMITER);
        stringLapTime.append(lapTime.toSecondsPart());
        stringLapTime.append(MINS_AND_MILLIS_DELIMITER);
        stringLapTime.append(lapTime.toMillisPart());
        return stringLapTime.toString();
    }

    public String getRacerLapTimeInfo() {
        return new StringBuilder().append(name)
                                .append(INFO_PART_DELIMITER)
                                .append(team)
                                .append(INFO_PART_DELIMITER)
                                .append(formattedLapTime)
                                .toString();
    }
}
