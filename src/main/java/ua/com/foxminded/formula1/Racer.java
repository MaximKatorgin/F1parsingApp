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


    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

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
    }



}
