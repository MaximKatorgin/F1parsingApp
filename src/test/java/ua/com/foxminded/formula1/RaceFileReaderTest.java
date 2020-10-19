package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceFileReaderTest {
    private ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void readFileToList_shouldReturn19Lines_whenGiven19LinesFile() {
        RaceFileReader raceFileReader = new RaceFileReader();
        File dataFile = new File(classLoader.getResource("end.log").getPath());

        List<String> ReadedData = raceFileReader.readFileToList(dataFile.getPath());

        assertEquals(19, ReadedData.size());
    }

    @Test
    void readFileToList_shouldReturnEmptyArrayList_whenGivenEmptyFileFile() {
        RaceFileReader raceFileReader = new RaceFileReader();
        File dataFile = new File(classLoader.getResource("empty.txt").getPath());

        List<String> ReadedData = raceFileReader.readFileToList(dataFile.getPath());

        assertEquals(0, ReadedData.size());
    }
}
