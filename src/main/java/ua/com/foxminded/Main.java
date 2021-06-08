package ua.com.foxminded;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        try {
            File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
            File start = new File(classLoader.getResource("start.log").getFile());
            File end = new File(classLoader.getResource("end.log").getFile());
            if ((abbreviations == null) || (start == null) || (end == null)) {
                throw new IllegalArgumentException();
            }
            RacersRepository racers = new RacersRepository(abbreviations, start, end);
            RacersListFormater formater = new RacersListFormater();
            formater.format(racers.getRacers()).forEach(racer -> System.out.println(racer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
