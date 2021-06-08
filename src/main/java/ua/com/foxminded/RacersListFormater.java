package ua.com.foxminded;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class RacersListFormater {

    private static final char SLAH = '|';

    public List<String> format(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException();
        }
        int maxNameLength = getMaxLengthOfField(racers, Racer::getName);
        int maxTeamLength = getMaxLengthOfField(racers, Racer::getTeam);
        String column1 = "%02d" + "." + "%-" + maxNameLength + "s" + SLAH;
        String column2 = "%-" + maxTeamLength + "s" + SLAH;
        String column3 = "%s";
        String formatPattern = column1 + column2 + column3;
        IntSupplier rowCounter = new IntSupplier() {
            int counter = 1;

            @Override
            public int getAsInt() {
                return counter++;
            }
        };
        List<String> racersList = racers
                .stream().sorted(Comparator.comparing(Racer::getLapTime)).map(racer -> String.format(formatPattern,
                        rowCounter.getAsInt(), racer.getName(), racer.getTeam(), formatLapTime(racer)))
                .collect(Collectors.toList());
        racersList.add(15, String.join("", Collections.nCopies(racersList.get(1).length(), "-")));
        return racersList;
    }

    private String formatLapTime(Racer racer) {
        long millis = racer.getLapTime().toMillis();
        return String.format("%02d:%02d.%03d", millis / 60000, (millis % 60000) / 1000, millis % 1000);
    }

    private int getMaxLengthOfField(List<Racer> racers, Function<Racer, String> function) {
        OptionalInt maxField = racers.stream().map(racer -> function.apply(racer)).mapToInt(String::length).max();
        int maxLengthField = maxField.orElseThrow();
        return maxLengthField;
    }
}
