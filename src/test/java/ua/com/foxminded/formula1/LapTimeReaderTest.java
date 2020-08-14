package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LapTimeReaderTest {

    @Test
    public void getQualificationTimes_shouldReturn19RowArray_whenGiven19RowFile() {
        File abbreviations = new File(Main.class.getResource("/abbreviations.txt").getPath());
        File startTimes = new File(Main.class.getResource("/start.log").getPath());
        File endTimes = new File(Main.class.getResource("/end.log").getPath());
        LapTimeReader lapTimeReader = new LapTimeReader(abbreviations, startTimes, endTimes);

        int arraySizeExpected = 19;

        ArrayList<Racer> timeList = lapTimeReader.getQualificationTimes();

        assertEquals(arraySizeExpected, timeList.size());
    }

    @Test
    public void getQualificationTimes_shouldThrowException_whenInputFileIsEmpty() {
        File abbreviations = new File(Main.class.getResource("/abbreviations.txt").getPath());
        File startTimes = new File(Main.class.getResource("/empty.txt").getPath());
        File endTimes = new File(Main.class.getResource("/end.log").getPath());
        LapTimeReader lapTimeReader = new LapTimeReader(abbreviations, startTimes, endTimes);

        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;

        assertThrows(expectedException, () -> lapTimeReader.getQualificationTimes());
    }

    @Test
    public void getQualificationTimes_shouldThrowException_whenInputFileNotExists() {
        File abbreviations = new File(Main.class.getResource("/abbreviations.txt").getPath());
        File startTimes = new File("");
        File endTimes = new File(Main.class.getResource("/end.log").getPath());
        LapTimeReader lapTimeReader = new LapTimeReader(abbreviations, startTimes, endTimes);

        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;

        assertThrows(expectedException, () -> lapTimeReader.getQualificationTimes());
    }

    @Test
    public void getQualificationTimes_shouldSortCorrectly_whenOrdinaryInput() {
        File abbreviations = new File(Main.class.getResource("/abbreviations.txt").getPath());
        File startTimes = new File(Main.class.getResource("/start.log").getPath());
        File endTimes = new File(Main.class.getResource("/end.log").getPath());
        LapTimeReader lapTimeReader = new LapTimeReader(abbreviations, startTimes, endTimes);

        Racer sebastian = new Racer("Sebastian Vettel", "FERRARI", "SVF");
        Racer kevin = new Racer("Kevin Magnussen", "HAAS FERRARI", "KMH");

        ArrayList<Racer> timeList = lapTimeReader.getQualificationTimes();

        assertEquals(sebastian.getAbbriveation(), timeList.get(0).getAbbriveation());
        assertEquals(kevin.getAbbriveation(), timeList.get(timeList.size() - 1).getAbbriveation());
    }

}
