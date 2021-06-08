package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacersListFormaterTest {

    private RacersListFormater formater;
    private List<Racer> racers;

    @BeforeEach
    void setUp() {
        formater = new RacersListFormater();
        racers = new ArrayList<>();
        racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
        racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.ofMillis(64415)));
        racers.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.ofMillis(72460)));
        racers.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", Duration.ofMillis(72639)));
        racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.ofMillis(72434)));
        racers.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", Duration.ofMillis(73028)));
        racers.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", Duration.ofMillis(72657)));
        racers.add(new Racer("CSR", "Carlos Sainz", "RENAULT", Duration.ofMillis(72950)));
        racers.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", Duration.ofMillis(72848)));
        racers.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.ofMillis(72941)));
        racers.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", Duration.ofMillis(73065)));
        racers.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", Duration.ofMillis(72463)));
        racers.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.ofMillis(72706)));
        racers.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", Duration.ofMillis(72829)));
        racers.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", Duration.ofMillis(72930)));
        racers.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.ofMillis(73179)));
        racers.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.ofMillis(73265)));
        racers.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.ofMillis(73323)));
        racers.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", Duration.ofMillis(73393)));

    }

    @Test
    void format_racersListIsNull_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> formater.format(null));
    }

    @Test
    void format() {
        List<String> expected = new ArrayList<>();
        expected.add("01.Sebastian Vettel |FERRARI                  |01:04.415");
        expected.add("02.Daniel Ricciardo |RED BULL RACING TAG HEUER|01:12.013");
        expected.add("03.Valtteri Bottas  |MERCEDES                 |01:12.434");
        expected.add("04.Lewis Hamilton   |MERCEDES                 |01:12.460");
        expected.add("05.Stoffel Vandoorne|MCLAREN RENAULT          |01:12.463");
        expected.add("06.Kimi Raikkonen   |FERRARI                  |01:12.639");
        expected.add("07.Fernando Alonso  |MCLAREN RENAULT          |01:12.657");
        expected.add("08.Sergey Sirotkin  |WILLIAMS MERCEDES        |01:12.706");
        expected.add("09.Charles Leclerc  |SAUBER FERRARI           |01:12.829");
        expected.add("10.Sergio Perez     |FORCE INDIA MERCEDES     |01:12.848");
        expected.add("11.Romain Grosjean  |HAAS FERRARI             |01:12.930");
        expected.add("12.Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|01:12.941");
        expected.add("13.Carlos Sainz     |RENAULT                  |01:12.950");
        expected.add("14.Esteban Ocon     |FORCE INDIA MERCEDES     |01:13.028");
        expected.add("15.Nico Hulkenberg  |RENAULT                  |01:13.065");
        expected.add("--------------------------------------------------------");
        expected.add("16.Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|01:13.179");
        expected.add("17.Marcus Ericsson  |SAUBER FERRARI           |01:13.265");
        expected.add("18.Lance Stroll     |WILLIAMS MERCEDES        |01:13.323");
        expected.add("19.Kevin Magnussen  |HAAS FERRARI             |01:13.393");

        assertEquals(expected, formater.format(racers));
    }

}
