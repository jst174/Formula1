package ua.com.foxminded;

import static java.lang.System.lineSeparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RacersListFormater {

    private static final char SLAH = '|';

    public String format(List<Racer> racers, int topRacers) {
        if (racers == null) {
            throw new IllegalArgumentException();
        }
        if (topRacers > racers.size()) {
            throw new IllegalArgumentException();
        }
        int maxNameLength = getMaxLengthOfField(racers, Racer::getName);
        int maxTeamLength = getMaxLengthOfField(racers, Racer::getTeam);
        StringBuilder result = new StringBuilder();
        String formatPattern = "%02d" + "." + "%-" + maxNameLength + "s" + SLAH + "%-" + maxTeamLength + "s" + SLAH
                + "%s";
        AtomicInteger numberOfPosition = new AtomicInteger(0);
        List<String> racersList = racers
                .stream().sorted(Comparator.comparing(Racer::getLapTime)).map(racer -> String.format(formatPattern,
                        numberOfPosition.incrementAndGet(), racer.getName(), racer.getTeam(), formatLapTime(racer)))
                .collect(Collectors.toList());
        racersList.add(topRacers, String.join("", Collections.nCopies(racersList.get(1).length(), "-")));
        racersList.forEach(racer -> result.append(racer + lineSeparator()));
        return result.toString();
    }

    private String formatLapTime(Racer racer) {
        long millis = racer.getLapTime().toMillis();
        return String.format("%02d:%02d.%03d", millis / 60000, (millis % 60000) / 1000, millis % 1000);
    }

    private int getMaxLengthOfField(List<Racer> racers, Function<Racer, String> function) {
        return racers.stream().map(function::apply).mapToInt(String::length).max().orElse(0);
    }
}
