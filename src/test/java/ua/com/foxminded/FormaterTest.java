package ua.com.foxminded;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormaterTest {

    private Formater formater;

    @BeforeEach
    void setUp() {
        formater = new Formater();
    }

    @Test
    void test() {
        List<Racer> racers = new LinkedList<>();
        StringBuilder expected = new StringBuilder();
        racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
        racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.ofMillis(64415)));
        expected.append("1.Sebastian Vettel |FERRARI                  |01:04.415" + lineSeparator());
        expected.append("2.Daniel Ricciardo |RED BULL RACING TAG HEUER|01:12.013" + lineSeparator());

        assertEquals(expected.toString(), formater.formatListRacers(racers));
    }

}
