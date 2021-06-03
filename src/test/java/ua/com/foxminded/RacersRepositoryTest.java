package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacersRepositoryTest {

    private RacersRepository racersRepository;

    @BeforeEach
    void setUp() throws Exception {
        File abbreviations = new File("src/test/resources/abbreviationsTest.txt");
        File start = new File("src/test/resources/startTest.log");
        File end = new File("src/test/resources/endTest.log");
        racersRepository = new RacersRepository(abbreviations, start, end);
    }

    @Test
    void getRacers_ShouldReturnListWithNewRacers() {
        List<Racer> expected = new LinkedList<>();
        expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.ofMillis(64415)));

        try {
            assertEquals(expected, racersRepository.getRacers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
