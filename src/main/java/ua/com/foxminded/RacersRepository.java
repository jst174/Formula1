package ua.com.foxminded;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacersRepository {

    private File abbreviations;
    private File startLogsFile;
    private File endLogsFile;

    public RacersRepository(File abbreviations, File startLogsFile, File endLogsFile) {
        this.abbreviations = abbreviations;
        this.startLogsFile = startLogsFile;
        this.endLogsFile = endLogsFile;
    }

    public List<Racer> getRacers() {
        List<Racer> racersList = new ArrayList<>();
        try (Stream<String> racers = Files.lines(Paths.get(abbreviations.getAbsolutePath()))) {
            Map<String, Duration> lapTime = getLapTime();
            racersList = racers.map(s -> s.split("_")).map(s -> new Racer(s[0], s[1], s[2], lapTime.get(s[0])))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return racersList;
    }

    private Map<String, Duration> getLapTime() throws IOException {
        Map<String, LocalDateTime> startLapTime = getTimeMap(startLogsFile);
        Map<String, LocalDateTime> endLapTime = getTimeMap(endLogsFile);
        return startLapTime.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, x -> Duration.between(x.getValue(), endLapTime.get(x.getKey()))));
    }

    private Map<String, LocalDateTime> getTimeMap(File file) {
        Map<String, LocalDateTime> time = new HashMap<>();
        final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        try (Stream<String> timeStream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            time = timeStream.map(s -> s.substring(0, 3) + " " + s.substring(3)).map(s -> s.split(" "))
                    .collect(toMap(k -> k[0], v -> LocalDateTime.parse(v[1], FORMATER)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }
}
