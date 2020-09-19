package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LapTimeReaderTest {
    private static LapTimeReader lapTimeReader = new LapTimeReader();

    @Test
    public void getQualificationTimes_shouldReturn19RowArray_whenGiven19RowFile() {
        lapTimeReader.setRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                Main.class.getResource("/start.log").getPath(),
                Main.class.getResource("/end.log").getPath());

        int arraySizeExpected = 19;

        ArrayList<Racer> timeList = lapTimeReader.getQualificationTimes();

        assertEquals(arraySizeExpected, timeList.size());
    }

    @Test
    public void getQualificationTimes_shouldThrowException_whenInputFileIsEmpty() {
        lapTimeReader.setRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                Main.class.getResource("/empty.txt").getPath(),
                Main.class.getResource("/end.log").getPath());

        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;

        assertDoesNotThrow(() -> lapTimeReader.getQualificationTimes());
    }

    @Test
    public void getQualificationTimes_shouldThrowException_whenInputFileNotExists() {
        lapTimeReader.setRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                "",
                Main.class.getResource("/end.log").getPath());


        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;

        assertDoesNotThrow(() -> lapTimeReader.getQualificationTimes());
    }

    @Test
    public void getQualificationTimes_shouldSortCorrectly_whenOrdinaryInput() {
        lapTimeReader.setRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                Main.class.getResource("/start.log").getPath(),
                Main.class.getResource("/end.log").getPath());


        Racer sebastian = new Racer("Sebastian Vettel", "FERRARI", "SVF");
        Racer kevin = new Racer("Kevin Magnussen", "HAAS FERRARI", "KMH");

        ArrayList<Racer> timeList = lapTimeReader.getQualificationTimes();

        assertEquals(sebastian.getAbbriveation(), timeList.get(0).getAbbriveation());
        assertEquals(kevin.getAbbriveation(), timeList.get(timeList.size() - 1).getAbbriveation());
    }

}
