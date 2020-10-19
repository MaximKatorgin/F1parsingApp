package ua.com.foxminded.formula1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RaceReportBuilderTest {
    private static ArrayList<Racer> racersList = new ArrayList<>();

    @BeforeAll
    public static void setUpRacersList() {
        Racer racer = new Racer("Kevin Magnussen", "HAAS FERRARI", "KMH");
        racer.setLapStartTime(LocalDateTime.of(2020,12, 12, 10, 10, 10));
        racer.setLapEndTime(LocalDateTime.of(2020,12, 12, 10, 11, 35));
        racer = new Racer("Sebastian Vettel", "FERRARI", "SVF");
        racer.setLapStartTime(LocalDateTime.of(2020,12, 12, 10, 10, 15));
        racer.setLapEndTime(LocalDateTime.of(2020,12, 12, 10, 11, 35));
        racersList.add(racer);
        racersList.add(racer);
    }

    @Test
    void getReportInList_shouldReturnSameList_whenGivenRacersList() {
        RaceReportBuilder raceReportBuilder = new RaceReportBuilder(racersList);

        assertEquals(racersList, raceReportBuilder.getReportInList());
    }

    @Test
    void getReportInList_shouldSortCorrectly_whenSecondIsFasterThanFirst() {
        RaceReportBuilder raceReportBuilder = new RaceReportBuilder(racersList);

        assertEquals(racersList.get(1).getAbbriveation(), raceReportBuilder.getReportInList().get(0).getAbbriveation());
    }
}
