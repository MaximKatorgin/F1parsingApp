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
    public void getQualificatoinTimes_shouldValidateThreeFiles_whenGivenThreeFiles() {
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

    @Test
    public void getQualificationTimes_shouldReturn19RowArray_whenGiven19RowFile() {
        ArrayList<Racer> timeList = raceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        int arraySizeExpected = 19;

        assertEquals(arraySizeExpected, timeList.size());
    }


    @Test
    public void getQualificationTimes_shouldSortCorrectly_whenOrdinaryInput() {
        ArrayList<Racer> timeList = raceFileParser.parseRaceFiles
                (classLoader.getResource("abbreviations.txt").getPath(),
                        classLoader.getResource("start.log").getPath(),
                        classLoader.getResource("end.log").getPath());

        RaceReportBuilder raceReportBuilder = new RaceReportBuilder(timeList);
        Racer sebastian = new Racer("Sebastian Vettel", "FERRARI", "SVF");
        Racer kevin = new Racer("Kevin Magnussen", "HAAS FERRARI", "KMH");

        assertEquals(sebastian.getAbbriveation(), raceReportBuilder.getReportInList().get(0).getAbbriveation());
        assertEquals(kevin.getAbbriveation(), raceReportBuilder.getReportInList().get(timeList.size() - 1).getAbbriveation());
    }

}
