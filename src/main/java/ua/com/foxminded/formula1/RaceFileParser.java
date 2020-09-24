package ua.com.foxminded.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class RaceFileParser {
    private final RaceFileValidator raceFileValidator = new RaceFileValidator();
    private final RaceFileReader raceFileReader = new RaceFileReader();

    private final HashMap<String, LocalDateTime> startTimeList = new HashMap<>();
    private final HashMap<String, LocalDateTime> endTimeList = new HashMap<>();

    public ArrayList<Racer> parseRaceFiles(String abbrivetionsPath, String startTimesPath, String endTimesPath) {
        raceFileValidator.validateRaceFile(abbrivetionsPath);
        raceFileValidator.validateRaceFile(startTimesPath);
        raceFileValidator.validateRaceFile(endTimesPath);

        return processQualification(
                raceFileReader.readFileToList(abbrivetionsPath),
                raceFileReader.readFileToList(startTimesPath),
                raceFileReader.readFileToList(endTimesPath));
    }

    private ArrayList<Racer> processQualification(List<String> abbriveationsList, List<String> startTimes, List<String> endTimes) {
        ArrayList<Racer> racersList = new ArrayList<>();
        createRacersList(abbriveationsList, racersList);
        processRacersTime(startTimes, this.startTimeList);
        processRacersTime(endTimes, this.endTimeList);
        setLapTimes(racersList, this.startTimeList, this.endTimeList);
        racersList.sort(Comparator.comparing(Racer::getLapTime));
        return racersList;
    }

    private void createRacersList(List<String> abbriveations, List<Racer> racersList) {
        try {
            Stream<String> abbriveationsStream = abbriveations.stream();
            abbriveationsStream.map(this::createRacerFromString).forEach(racersList::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processRacersTime(List<String> timeList, HashMap<String, LocalDateTime> racersTimeHashMap) {
        try{
            Stream<String> lapTimesStream = timeList.stream();
            lapTimesStream.forEach(line -> racersTimeHashMap.put(line.substring(0,3), this.parseDateTime(line.substring(3))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setLapTimes(List<Racer> racersList, Map<String, LocalDateTime> startTimeList, Map<String, LocalDateTime> endTimeList) {
        racersList.forEach( racer -> racer.setLapTime(startTimeList.get(racer.getAbbriveation()),
                endTimeList.get(racer.getAbbriveation())));
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
