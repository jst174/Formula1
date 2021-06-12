package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.lineSeparator;

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
    }

    @Test
    void format_racersListIsNull_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> formater.format(null, 0));
    }

    @Test
    void format_topRacersMoreThanRacerListSize_shouldReturnResulWithoutSeparatorLine() {
        StringBuilder expected = new StringBuilder();
        expected.append("01.Sebastian Vettel|FERRARI                  |01:04.415" + lineSeparator());
        expected.append("02.Daniel Ricciardo|RED BULL RACING TAG HEUER|01:12.013" + lineSeparator());
        expected.append("03.Lewis Hamilton  |MERCEDES                 |01:12.460" + lineSeparator());

        assertEquals(expected.toString(), formater.format(racers, 4));
    }

    @Test
    void format_ShouldReturnResult() {
        StringBuilder expected = new StringBuilder();
        expected.append("01.Sebastian Vettel|FERRARI                  |01:04.415" + lineSeparator());
        expected.append("-------------------------------------------------------" + lineSeparator());
        expected.append("02.Daniel Ricciardo|RED BULL RACING TAG HEUER|01:12.013" + lineSeparator());
        expected.append("03.Lewis Hamilton  |MERCEDES                 |01:12.460" + lineSeparator());

        assertEquals(expected.toString(), formater.format(racers, 1));
    }

}
