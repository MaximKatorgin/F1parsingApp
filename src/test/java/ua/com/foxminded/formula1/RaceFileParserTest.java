package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RaceFileParserTest {
    private static RaceFileParser raceFileParser = new RaceFileParser();

    @Test
    public void getQualificationTimes_shouldReturn19RowArray_whenGiven19RowFile() {
        ArrayList<Racer> timeList = raceFileParser.parseRaceFiles(
                Main.class.getResource("/abbreviations.txt").getPath(),
                Main.class.getResource("/start.log").getPath(),
                Main.class.getResource("/end.log").getPath());

        int arraySizeExpected = 19;


        assertEquals(arraySizeExpected, timeList.size());
    }

    @Test
    public void getQualificationTimes_shouldNotThrowException_whenInputFileIsEmpty() {
        assertDoesNotThrow(() -> raceFileParser.parseRaceFiles
                (Main.class.getResource("/abbreviations.txt").getPath(),
                        Main.class.getResource("/empty.txt").getPath(),
                        Main.class.getResource("/end.log").getPath()));
    }

    @Test
    public void getQualificationTimes_shouldNotThrowException_whenInputFileNotExists() {
        assertDoesNotThrow(() -> raceFileParser.parseRaceFiles
                (Main.class.getResource("/abbreviations.txt").getPath(),
                        "",
                        Main.class.getResource("/end.log").getPath()));
    }

    @Test
    public void getQualificationTimes_shouldSortCorrectly_whenOrdinaryInput() {
        ArrayList<Racer> timeList = raceFileParser.parseRaceFiles
                (Main.class.getResource("/abbreviations.txt").getPath(),
                Main.class.getResource("/start.log").getPath(),
                Main.class.getResource("/end.log").getPath());

        RaceReportBuilder raceReportBuilder = new RaceReportBuilder(timeList);
        Racer sebastian = new Racer("Sebastian Vettel", "FERRARI", "SVF");
        Racer kevin = new Racer("Kevin Magnussen", "HAAS FERRARI", "KMH");


        assertEquals(sebastian.getAbbriveation(), raceReportBuilder.getReportInList().get(0).getAbbriveation());
        assertEquals(kevin.getAbbriveation(), raceReportBuilder.getReportInList().get(timeList.size() - 1).getAbbriveation());
    }

}
