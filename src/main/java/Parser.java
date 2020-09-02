import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static void main(String[] args) {
        String path = "D:\\analytical_tool\\data.txt";
        readFromFile(path);
    }

    public static void readFromFile(String path) {
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
            e.printStackTrace();
        }

        for (int i = 0; i < splitter.length; i++) {
            int sum = 0;
            int count = 0;
            int average;

            String dateDStart = "";
            String dateDEnd = "";
            String dateC = "";
            String oneDateCheckD = "";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            LocalDate ddateS = LocalDate.now();
            LocalDate ddateE = LocalDate.now();
            LocalDate oneDateCheck = LocalDate.now();
            LocalDate cdateInit;

            if (splitter[i][0] == null) {
                break;
            }

            if (splitter[i][0].contains("D")) {
                for (int j = 0; j < i; j++) {
                    if (splitter[i][4].contains("-")) {
                        String[] split = splitter[i][4].split("-");
                        for (int k = 0; k < split.length; k++) {
                            dateDStart = split[0];
                            ddateS = LocalDate.parse(dateDStart, formatter);
                            dateDEnd = split[1];
                            ddateE = LocalDate.parse(dateDEnd, formatter);
                        }
                    } else {
                        oneDateCheckD = splitter[i][4];
                        oneDateCheck = LocalDate.parse(oneDateCheckD, formatter);
                    }

                    if (splitter[j][0].contains("C")) {
                        dateC = splitter[j][4];
                        cdateInit = LocalDate.parse(dateC, formatter);

                        if (((splitter[i][1].equals(splitter[j][1].substring(0, 1))
                                && (dateCheck(ddateS, ddateE, cdateInit)
                                || singleDateCheck(cdateInit, oneDateCheck)))
                                || splitter[i][1].equals("*")
                                || splitter[j][1].equals("*"))
                                || splitter[i][2].equals(splitter[j][2].substring(0, 2))
                                && ((splitter[i][2].equals(splitter[j][2].substring(0, 1))
                                || splitter[i][2].equals("*")
                                || splitter[j][2].equals("*")))) {
                            count++;
                            sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                            continue;
                        }

                        if (splitter[i][1].equals(splitter[j][1])
                                && splitter[i][2].equals(splitter[j][2].substring(0, 1))
                                && dateCheck(ddateS, ddateE, cdateInit)) {
                            count++;
                            sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                        }
                    }
                }

                if (count > 1) {
                    average = sum / count;
                    System.out.println(average);
                } else if (sum == 0) {
                    System.out.println("-");
                } else {
                    System.out.println(sum);
                }
            }
        }
    }

    public static boolean dateCheck(LocalDate ddateS, LocalDate ddateE, LocalDate cdateInit) {
        if (ddateS.compareTo(cdateInit) * cdateInit.compareTo(ddateE) > 0) {
            return true;
        } else {
            return cdateInit.equals(ddateS) || cdateInit.equals(ddateE);
        }
    }

    public static boolean singleDateCheck(LocalDate cdateInit, LocalDate oneDateCheck) {
        return cdateInit.equals(oneDateCheck);
    }
}
