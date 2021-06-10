package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacersRepositoryTest {

    private RacersRepository racersRepository;

    @BeforeEach
    void setUp() throws IOException {
        ClassLoader classLoader = RacersRepositoryTest.class.getClassLoader();
        File abbreviations = new File(classLoader.getResource("abbreviationsTest.txt").getFile());
        File start = new File(classLoader.getResource("startTest.log").getFile());
        File end = new File(classLoader.getResource("endTest.log").getFile());
        racersRepository = new RacersRepository(abbreviations, start, end);

    }

    @Test
    void getRacers_ShouldReturnListWithNewRacers() {
        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.ofMillis(64415)));
        expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.ofMillis(72460)));

        try {
            assertEquals(expected, racersRepository.getRacers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
