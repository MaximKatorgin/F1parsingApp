package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RaceFileValidatorTest {

    private ClassLoader classLoader = getClass().getClassLoader();
    private RaceFileValidator raceFileValidator = new RaceFileValidator();

    @Test
    void validateRaceFile_shouldNotThrowException_whenGivenCorrectFilePath() {
        assertDoesNotThrow(() -> raceFileValidator.validateRaceFile(classLoader.getResource("start.log").getPath()));
    }

    @Test
    void validateRaceFile_shouldNotThrowException_whenGivenNullFilePath() {
        assertDoesNotThrow(() -> raceFileValidator.validateRaceFile(null));
    }

    @Test
    void validateRaceFile_shouldNotThrowException_whenGivenEmptyFilePath() {
        assertDoesNotThrow(() -> raceFileValidator.validateRaceFile(""));
    }

    @Test
    void validateRaceFile_shouldNotThrowException_whenGivenDirectoryInsteadFile() {
        assertDoesNotThrow(() -> raceFileValidator.validateRaceFile(Paths.get("/resources").toString()));
    }

    @Test
    void validateRaceFile_shouldNotThrowException_whenGivenFileNotExists() {
        assertDoesNotThrow(() -> raceFileValidator.validateRaceFile(Paths.get("/resources/notExists.txt").toString()));
    }

}
