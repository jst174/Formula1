package ua.com.foxminded;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        try {
            File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
            File start = new File(classLoader.getResource("start.log").getFile());
            File end = new File(classLoader.getResource("end.log").getFile());
            RacersRepository racers = new RacersRepository(abbreviations, start, end);
            RacersListFormater formater = new RacersListFormater();
            System.out.println(formater.format(racers.getRacers(), 15));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
