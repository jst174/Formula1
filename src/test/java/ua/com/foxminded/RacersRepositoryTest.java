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
    private File abbreviations;
    private File start;
    private File end;

    @BeforeEach
    void setUp() throws IOException {
        ClassLoader classLoader = RacersRepositoryTest.class.getClassLoader();
        abbreviations = new File(classLoader.getResource("abbreviationsTest.txt").getFile());
        start = new File(classLoader.getResource("startTest.log").getFile());
        end = new File(classLoader.getResource("endTest.log").getFile());

    }

    @Test
    void getRacers_abbreviationsIsNull_ShouldThrowExcception() {
        racersRepository = new RacersRepository(null, start, end);

        assertThrows(RuntimeException.class, () -> racersRepository.getRacers());
    }

    @Test
    void getRacers_startIsNull_ShouldThrowExcception() {
        racersRepository = new RacersRepository(abbreviations, null, end);

        assertThrows(RuntimeException.class, () -> racersRepository.getRacers());
    }

    @Test
    void getRacers_endIsNull_ShouldThrowExcception() {
        racersRepository = new RacersRepository(abbreviations, start, null);

        assertThrows(RuntimeException.class, () -> racersRepository.getRacers());
    }

    @Test
    void getRacers_ShouldReturnListWithNewRacers() {
        racersRepository = new RacersRepository(abbreviations, start, end);
        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.ofMillis(64415)));
        expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.ofMillis(72460)));

        assertEquals(expected, racersRepository.getRacers());
    }

}
