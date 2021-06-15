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
    private static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    public RacersRepository(File abbreviations, File startLogsFile, File endLogsFile) {
        this.abbreviations = abbreviations;
        this.startLogsFile = startLogsFile;
        this.endLogsFile = endLogsFile;
    }

    public List<Racer> getRacers() {
        try (Stream<String> racersStream = Files.lines(Paths.get(abbreviations.getAbsolutePath()))) {
            Map<String, Duration> lapTime = getLapTime();
            return racersStream.map(s -> s.split("_")).map(s -> new Racer(s[0], s[1], s[2], lapTime.get(s[0])))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Duration> getLapTime() throws IOException {
        Map<String, LocalDateTime> startLapTime = getTime(startLogsFile);
        Map<String, LocalDateTime> endLapTime = getTime(endLogsFile);
        return startLapTime.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, x -> Duration.between(x.getValue(), endLapTime.get(x.getKey()))));
    }
    

    private Map<String, LocalDateTime> getTime(File file) {
        try (Stream<String> timeStream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return timeStream.map(s -> s.split("(?<=\\D{3})"))
                    .collect(toMap(k -> k[0], v -> LocalDateTime.parse(v[1], FORMATER)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
