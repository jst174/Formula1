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
        expected.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", Duration.ofMillis(72639)));
        expected.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.ofMillis(72434)));
        expected.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", Duration.ofMillis(73028)));
        expected.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", Duration.ofMillis(72657)));
        expected.add(new Racer("CSR", "Carlos Sainz", "RENAULT", Duration.ofMillis(72950)));
        expected.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", Duration.ofMillis(72848)));
        expected.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.ofMillis(72941)));
        expected.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", Duration.ofMillis(73065)));
        expected.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", Duration.ofMillis(72463)));
        expected.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.ofMillis(72706)));
        expected.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", Duration.ofMillis(72829)));
        expected.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", Duration.ofMillis(72930)));
        expected.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.ofMillis(73179)));
        expected.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.ofMillis(73265)));
        expected.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.ofMillis(73323)));
        expected.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", Duration.ofMillis(73393)));

        try {
            assertEquals(expected, racersRepository.getRacers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
