package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;


class RaceFileParserTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    private RaceFileValidator mockedRaceFileValidator = mock(RaceFileValidator.class);
    private RaceFileReader mockedRaceFileReader = mock(RaceFileReader.class);

    @Test
    public void getQualificationTimes_shouldReadFilesThreeTimes_whenGivenThreeFiles() {
        RaceFileParser mockedRaceFileParser = new RaceFileParser(mockedRaceFileValidator, mockedRaceFileReader);

        List<Racer> timeList = mockedRaceFileParser.parseRaceFiles(
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

        List<Racer> timeList = mockedRaceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        verify(mockedRaceFileValidator,times(3)).validateRaceFile(anyString());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("abbreviations.txt").getPath());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("start.log").getPath());
        verify(mockedRaceFileValidator).validateRaceFile(classLoader.getResource("end.log").getPath());
    }
}
