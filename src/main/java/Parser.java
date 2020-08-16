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

        String checkD = "";
        String checkC = "";

        for (int i = 0; i < splitter.length; i++) {
            if (splitter[i][0].contains("D")) {
                for (String s : splitter[i]) {
                    System.out.print(s + " ");
                }
                for (int j = 0; j < i; j++) {
                    if (splitter[j][0].contains("C")) {
                        for (String s : splitter[j]) {

                        }
                        System.out.println();
                    }
                }
            }
        }
    }
}
