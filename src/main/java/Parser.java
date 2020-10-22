import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.run();
    }

    private void run() {
        String path = "src\\main\\resources\\data.txt";
        String[][] splitter = ReadFromFile.readFromFile(path);
        Parser parser = new Parser();
        parser.parser(splitter);
    }

    private void parser(String[][] splitter) {
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
                        LocalDate timeLineTime
                                = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);

                        if (timeLineTime.isBefore(timeGap[0]) || timeLineTime.isAfter(timeGap[1])) {
                            continue;
                        }
                    } else {
                        LocalDate queryTime
                                = LocalDate.parse(queryDate, FORMATTER);
                        LocalDate timeLineTime
                                = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);

                        if (!queryTime.isEqual(timeLineTime)) {
                            continue;
                        }
                    }

                    sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                    count++;
                }

                calculateResult(count, sum);
            }
        }
    }

    private void calculateResult(int count, int sum) {
        if (count > 0) {
            System.out.println(sum / count);
        } else {
            System.out.println("-");
        }
    }
}
