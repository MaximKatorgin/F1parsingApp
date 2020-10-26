package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceFileParserIntegrationTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    RaceFileParser raceFileParser = new RaceFileParser();
    @Test
    public void getQualificationTimes_shouldReturn19RowArray_whenGiven19RowFile() {
        List<Racer> timeList = raceFileParser.parseRaceFiles(
                classLoader.getResource("abbreviations.txt").getPath(),
                classLoader.getResource("start.log").getPath(),
                classLoader.getResource("end.log").getPath());

        int arraySizeExpected = 19;

        assertEquals(arraySizeExpected, timeList.size());
    }

    @Test
    public void getQualificationTimes_shouldSortCorrectly_whenOrdinaryInput() {
        List<Racer> timeList = raceFileParser.parseRaceFiles
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
