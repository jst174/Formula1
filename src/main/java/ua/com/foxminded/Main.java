package ua.com.foxminded;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {
        try {
            File abbreviations = new File("src/main/resources/abbreviations.txt");
            File start = new File("src/main/resources/start.log");
            File end = new File("src/main/resources/end.log");
            RacersRepository racers = new RacersRepository(abbreviations, start, end);
            Formater formater = new Formater();

            System.out.println(formater.formatListRacers(racers.getRacers()));
            // System.out.println(racers.getRacers().stream().sorted(Comparator.comparing(Racer::getLapTime)).collect(Collectors.toList()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
