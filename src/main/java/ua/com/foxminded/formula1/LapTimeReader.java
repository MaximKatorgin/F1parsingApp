package ua.com.foxminded.formula1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

public class LapTimeReader {
    private final File abbriveations;
    private final File startTimes;
    private final File endTimes;
    private final ArrayList<Racer> racers = new ArrayList<>();

    private final HashMap<String, LocalDateTime> startTimeList = new HashMap<>();
    private final HashMap<String, LocalDateTime> endTimeList = new HashMap<>();

    public LapTimeReader(File abbriveations, File startTimes, File endTimes) {
        this.abbriveations = abbriveations;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
    }

    public ArrayList<Racer> getQualificationTimes() {
        FileCheck fileCheck = new FileCheck();
        fileCheck.checkFiles(abbriveations, startTimes, endTimes);
        processQualification();
        racers.sort(Comparator.comparing(Racer::getLapTime));
        return racers;
    }

    private void processQualification() {
        createRacersList();
        processRacersStartTimes();
        processRacersEndTimes();
        setLapTimes();
    }

    private void createRacersList() {
        try {
            Stream<String> abbriveationsStream = Files.lines(Paths.get(abbriveations.getAbsolutePath()));
            abbriveationsStream.map(this::createRacerFromString).forEach(this.racers::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processRacersStartTimes() {
        try {
            Stream<String> lapStartTimesStream = Files.lines(Paths.get(startTimes.getAbsolutePath()));
            lapStartTimesStream.forEach(line -> this.startTimeList.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processRacersEndTimes() {
        try {
            Stream<String> lapStartTimesStream = Files.lines(Paths.get(endTimes.getAbsolutePath()));
            lapStartTimesStream.forEach(line -> this.endTimeList.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (IOException ex) {
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
