package ua.com.foxminded;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacersRepository {

    private File abbreviations;
    private File start;
    private File end;
    private Map<String, Duration> lapTime = new HashMap<>();
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    public RacersRepository(File abbreviations, File start, File end) {
        this.abbreviations = abbreviations;
        this.start = start;
        this.end = end;
    }

    public List<Racer> getRacers() throws IOException {
        calculateLapTime();
        return Files.lines(Paths.get(abbreviations.getAbsolutePath())).map(s -> s.split("_"))
                .map(s -> new Racer(s[0], s[1], s[2], lapTime.get(s[0]))).collect(Collectors.toList());
    }

    private Map<String, LocalDateTime> getStartLapTime() throws IOException {
        return Files.lines(Paths.get(start.getAbsolutePath())).map(s -> s.substring(0, 3) + " " + s.substring(3))
                .map(s -> s.split(" ", 2)).collect(toMap(k -> k[0], v -> LocalDateTime.parse(v[1], formater)));
    }

    private Map<String, LocalDateTime> getEndLapTime() throws IOException {
        return Files.lines(Paths.get(end.getAbsolutePath())).map(s -> s.substring(0, 3) + " " + s.substring(3))
                .map(s -> s.split(" ")).collect(toMap(k -> k[0], v -> LocalDateTime.parse(v[1], formater)));
    }

    private void calculateLapTime() throws IOException {
        Map<String, LocalDateTime> end = getEndLapTime();
        lapTime = getStartLapTime().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, x -> Duration.between(x.getValue(), end.get(x.getKey()))));
    }
}
