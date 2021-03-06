package ua.com.foxminded.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class RaceFileParser {
    private RaceFileValidator raceFileValidator;
    private RaceFileReader raceFileReader;

    public RaceFileParser() {
        this.raceFileValidator = new RaceFileValidator();
        this.raceFileReader = new RaceFileReader();
    }

    public RaceFileParser(RaceFileValidator raceFileValidator, RaceFileReader raceFileReader) {
        this.raceFileReader = raceFileReader;
        this.raceFileValidator = raceFileValidator;
    }

    public List<Racer> parseRaceFiles(String abbriveationsPath, String startTimesPath, String endTimesPath) {
        raceFileValidator.validateRaceFile(abbriveationsPath);
        raceFileValidator.validateRaceFile(startTimesPath);
        raceFileValidator.validateRaceFile(endTimesPath);

        return processQualification(
                raceFileReader.readFileToList(abbriveationsPath),
                raceFileReader.readFileToList(startTimesPath),
                raceFileReader.readFileToList(endTimesPath));
    }

    private ArrayList<Racer> processQualification(List<String> abbriveationsList, List<String> startTimes, List<String> endTimes) {
        ArrayList<Racer> racersList = new ArrayList<>();
        createRacersList(abbriveationsList, racersList, startTimes, endTimes);
        return racersList;
    }

    private void createRacersList(List<String> abbriveations, List<Racer> racersList, List<String> startTimes, List<String> endTimes) {
        HashMap<String, LocalDateTime> startTimeList = processRacersTime(startTimes);
        HashMap<String, LocalDateTime> endTimeList = processRacersTime(endTimes);
        try {
            Stream<String> abbriveationsStream = abbriveations.stream();
            abbriveationsStream.map(this::createRacerFromString).map(racer -> {
                racer.setLapStartTime(startTimeList.get(racer.getAbbriveation()));
                racer.setLapEndTime(endTimeList.get(racer.getAbbriveation()));
                return racer;})
                    .forEach(racersList::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private HashMap<String, LocalDateTime> processRacersTime(List<String> timeList) {
        HashMap<String, LocalDateTime> racersTimeHashMap = new HashMap<>();
        try{
            Stream<String> lapTimesStream = timeList.stream();
            lapTimesStream.forEach(line -> racersTimeHashMap.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return racersTimeHashMap;
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
