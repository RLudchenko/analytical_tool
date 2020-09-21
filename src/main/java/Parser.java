import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static void main(String[] args) {
        String path = "D:\\analytical_tool\\src\\main\\resources\\data.txt";
        String[][] splitter = readFromFile(path);
        parser(splitter);
    }

    private static String[][] readFromFile(String path) {
        String line = "";

        String[][] splitter = new String[7][6];
        int currentLine = 0;

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            reader.readLine();

            line = reader.readLine();

            while (line != null) {
                splitter[currentLine] = line.split(" ");
                currentLine++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error has occurred: " + e);
        }

        return splitter;
    }

    private static void parser(String[][] splitter) {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 0; i < splitter.length; i++) {
            int sum = 0;
            int count = 0;
            if (splitter[i][0].contains("D")) {
                queryCompare:
                for (int j = 0; j < i; j++) {
                    if (!splitter[j][0].contains("C")) {
                        continue;
                    }

                    for (int k = 1; k < splitter[i].length - 2; k++) {
                        if (splitter[j][k].equals("*") || splitter[i][k].equals("*")) {
                            continue;
                        }

                        if (!splitter[j][k].matches("^" + splitter[i][k] + "(.*)")) {
                            continue queryCompare;
                        }
                    }

                    String queryDate = splitter[i][splitter[i].length - 1];
                    if (queryDate.contains("-")) {
                        String[] queryDateGap = queryDate.split("-");

                        LocalDate[] timeGap = { LocalDate.parse(queryDateGap[0], FORMATTER),
                                LocalDate.parse(queryDateGap[1], FORMATTER)};
                        LocalDate timeLineTime = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);

                        if (timeLineTime.isBefore(timeGap[0]) || timeLineTime.isAfter(timeGap[1])) {
                            continue queryCompare;
                        }

                    } else {
                        LocalDate queryTime = LocalDate.parse(queryDate, FORMATTER);
                        LocalDate timeLineTime = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);

                        if (!queryTime.isEqual(timeLineTime)) {
                            continue queryCompare;
                        }
                    }

                    sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                    count++;
                }

                if (count > 0) {
                    System.out.println(sum / count);
                } else {
                    System.out.println("-");
                }
            }
        }
    }
}
