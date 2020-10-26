package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class RaceFileParserTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    private RaceFileValidator mockedRaceFileValidator = mock(RaceFileValidator.class);
    private RaceFileReader mockedRaceFileReader = mock(RaceFileReader.class);


    @Test
    public void getQualificationTimes_shouldReturnListOfRacers_whenGivenListsWithData() {
        doNothing().when(mockedRaceFileValidator).validateRaceFile(anyString());
        when(mockedRaceFileReader.readFileToList(classLoader.getResource("abbreviations.txt").getPath()))
                .thenReturn(Arrays.asList(
                        "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                        "SVF_Sebastian Vettel_FERRARI",
                        "LHM_Lewis Hamilton_MERCEDES"));
        when(mockedRaceFileReader.readFileToList(classLoader.getResource("start.log").getPath()))
                .thenReturn(Arrays.asList(
                        "SVF2018-05-24_12:02:58.917",
                        "LHM2018-05-24_12:18:20.125",
                        "DRR2018-05-24_12:14:12.054"));
        when(mockedRaceFileReader.readFileToList(classLoader.getResource("end.log").getPath()))
                .thenReturn(Arrays.asList(
                        "DRR2018-05-24_12:15:24.067",
                        "SVF2018-05-24_12:04:03.332",
                        "LHM2018-05-24_12:19:32.585"));
        RaceFileParser mockedRaceFileParser = new RaceFileParser(mockedRaceFileValidator, mockedRaceFileReader);

        List<Racer> timeList = mockedRaceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        assertEquals(timeList.size(), 3);

    }

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
