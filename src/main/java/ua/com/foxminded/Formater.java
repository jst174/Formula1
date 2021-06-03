package ua.com.foxminded;

import static java.lang.System.lineSeparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Formater {

    private static final char SLAH = '|';
    private static final char SPACE = ' ';

    public String formatListRacers(List<Racer> racers) {
        int maxNameLength = getMaxLengthOfField(racers, Racer::getName);
        int maxTeamLength = getMaxLengthOfField(racers, Racer::getTeam);
        StringBuilder result = new StringBuilder();
        racers = racers.stream().sorted(Comparator.comparing(Racer::getLapTime)).collect(Collectors.toList());
        for (int i = 0; i < racers.size(); i++) {
            result.append(i + 1).append(".").append(racers.get(i).getName());
            if (i + 1 < 10) {
                result.append(SPACE);
            }
            result.append(addSeparator(racers, maxNameLength - racers.get(i).getName().length()))
                    .append(racers.get(i).getTeam())
                    .append(addSeparator(racers, maxTeamLength - racers.get(i).getTeam().length()))
                    .append(formatLapTime(racers.get(i))).append(lineSeparator());

            if (i == 14) {
                for (int j = 0; j < maxNameLength + maxNameLength + 22; j++) {
                    result.append("-");
                }
                result.append(lineSeparator());
            }
        }
        return result.toString();
    }

    private String formatLapTime(Racer racer) {
        long millis = racer.getLapTime().toMillis();
        return String.format("%02d:%02d.%03d", millis / 60000, (millis % 60000) / 1000, millis % 1000);
    }

    private int getMaxLengthOfField(List<Racer> racers, Function<Racer, String> function) {
        int maxLengthField = function.apply(racers.get(0)).length();
        for (int i = 0; i < racers.size(); i++) {
            if (function.apply(racers.get(i)).length() > maxLengthField) {
                maxLengthField = function.apply(racers.get(i)).length();
            }
        }
        return maxLengthField;
    }

    private String addSeparator(List<Racer> racers, int length) {
        String separator = "";
        for (int i = 0; i < length; i++) {
            separator += SPACE;
        }
        return separator + SLAH;
    }

}
