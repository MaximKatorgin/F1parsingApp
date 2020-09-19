package ua.com.foxminded.formula1;

public class Main {
    public static void main(String[] args) {
        try {
            LapTimeReader lapTimeReader = new LapTimeReader();
            lapTimeReader.setRaceFiles(Main.class.getResource("/abbreviations.txt").getPath(),
                                        Main.class.getResource("/start.log").getPath(),
                                        Main.class.getResource("/end.log").getPath());
            lapTimeReader.printQualificationTimes();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
