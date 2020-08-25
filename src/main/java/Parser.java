import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static void main(String[] args) throws IOException {
        String line = "";

        String[][] splitter = new String[7][6];
        int currentLine = 0;

        try {
            File file = new File("D:\\analytical_tool\\data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String linesCount = reader.readLine();

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
            if (splitter[i][0].contains("D")) {
                for (int j = 0; j < i; j++) {
                    if (splitter[j][0].contains("C")) {
                        if (splitter[i][1].equals(splitter[j][1].substring(0, 1))
                                && splitter[i][2].equals(splitter[j][2].substring(0, 1))
                                || splitter[i][2].equals("*")) {
                            count++;
                            sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                            continue;
                        }

                        if (splitter[i][1].equals(splitter[j][1])
                                && splitter[i][2].equals(splitter[j][2].substring(0, 1))) {
                            count++;
                            sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
                        }
                    }
                }

                if (count > 1) {
                    average = sum / count;
                    System.out.println(average);
                } else {
                    System.out.println(sum);
                }
            }
        }
    }
}


