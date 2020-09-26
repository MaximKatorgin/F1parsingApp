package ua.com.foxminded.formula1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Racer {
    private String name;
    private String team;
    private String abbriveation;
    private LocalDateTime lapStartTime;
    private LocalDateTime lapEndTime;


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
        return Duration.between(lapStartTime, lapEndTime);
    }

    public String getAbbriveation() {
        return abbriveation;
    }

    public void setLapStartTime(LocalDateTime lapStartTime) {
        this.lapStartTime = lapStartTime;
    }

    public void setLapEndTime(LocalDateTime lapEndTime) {
        this.lapEndTime = lapEndTime;
    }
}
