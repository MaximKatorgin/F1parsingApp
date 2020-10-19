package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RaceFileParserTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    RaceFileParser raceFileParser = new RaceFileParser();
    private RaceFileValidator mockedRaceFileValidator = mock(RaceFileValidator.class);
    private RaceFileReader mockedRaceFileReader = mock(RaceFileReader.class);

    @Test
    public void getQualificationTimes_shouldReadFilesThreeTimes_whenGivenThreeFiles() {
        RaceFileParser mockedRaceFileParser = new RaceFileParser(mockedRaceFileValidator, mockedRaceFileReader);

        ArrayList<Racer> timeList = mockedRaceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        verify(mockedRaceFileReader,times(3)).readFileToList(anyString());
        verify(mockedRaceFileReader).readFileToList(classLoader.getResource("abbreviations.txt").getPath());
        verify(mockedRaceFileReader).readFileToList(classLoader.getResource("start.log").getPath());
        verify(mockedRaceFileReader).readFileToList(classLoader.getResource("end.log").getPath());
    }

    @Test
    public void getQualificationTimes_shouldValidateThreeFiles_whenGivenThreeFiles() {
        RaceFileParser mockedRaceFileParser = new RaceFileParser(mockedRaceFileValidator, mockedRaceFileReader);

        ArrayList<Racer> timeList = mockedRaceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        verify(mockedRaceFileValidator,times(3)).validateRaceFile(anyString());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("abbreviations.txt").getPath());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("start.log").getPath());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("end.log").getPath());
    }

    @Test
    public void getQualificationTimes_shouldNotThrowException_whenWrongTimeFormat() {
        doNothing().when(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("empty.txt").getPath());

        assertDoesNotThrow(() -> raceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("empty.txt").getPath(),
                classLoader.getResource("end.log").getPath()));
    }

    @Test
    public void getQualificationTimes_shouldNotThrowException_whenWrongAbbreviatonFormat() {
        doNothing().when(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("empty.txt").getPath());

        assertDoesNotThrow(() -> raceFileParser.parseRaceFiles(
                classLoader.getResource("empty.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath()));
    }






}
