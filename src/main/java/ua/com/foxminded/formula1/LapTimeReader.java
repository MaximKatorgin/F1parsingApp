package ua.com.foxminded.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

public class LapTimeReader {
    private ArrayList<String> abbriveations;
    private ArrayList<String> startTimes;
    private ArrayList<String> endTimes;
    private final ArrayList<Racer> racers = new ArrayList<>();

    private final HashMap<String, LocalDateTime> startTimeList = new HashMap<>();
    private final HashMap<String, LocalDateTime> endTimeList = new HashMap<>();

    public void setRaceFiles(String abbrivetions, String startTimes, String endTimes) {
        FileCheck fileCheck = new FileCheck(abbrivetions, startTimes, endTimes);
        fileCheck.checkFiles();
        this.abbriveations = fileCheck.getAbbriveationsData();
        this.startTimes = fileCheck.getStartTimesData();
        this.endTimes = fileCheck.getEndTimesData();
    }

    public ArrayList<Racer> getQualificationTimes() {
        processQualification();
        racers.sort(Comparator.comparing(Racer::getLapTime));
        return racers;
    }

    public void printQualificationTimes() {
        processQualification();
        LapTimeReport lapTimeReport = new LapTimeReport(this.racers);
        lapTimeReport.printReport();
    }

    private void processQualification() {
        createRacersList();
        processRacersStartTimes();
        processRacersEndTimes();
        setLapTimes();
    }

    private void createRacersList() {
        try {
            Stream<String> abbriveationsStream = abbriveations.stream();
            abbriveationsStream.map(this::createRacerFromString).forEach(this.racers::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processRacersStartTimes() {
        try {
            Stream<String> lapStartTimesStream = startTimes.stream();
            lapStartTimesStream.forEach(line -> this.startTimeList.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processRacersEndTimes() {
        try {
            Stream<String> lapStartTimesStream = endTimes.stream();
            lapStartTimesStream.forEach(line -> this.endTimeList.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setLapTimes() {
        racers.forEach( racer -> racer.setLapTime(startTimeList.get(racer.getAbbriveation()), endTimeList.get(racer.getAbbriveation())));
    }

    private LocalDateTime parseDateTime(String line) {
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return LocalDateTime.parse(line, formatter);
    }


    private Racer createRacerFromString(String line) {
        String[] splitedRacersLine = line.split("_");
        return new Racer(splitedRacersLine[1], splitedRacersLine[2], splitedRacersLine[0]);
    }
}
