import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ReadFromFile {
    private static final int MAX_NUMBER_OF_ELEMENTS_IN_LINE = 6;

    public String[][] readFromFile(String path) {
        String line = "";
        int numberOfLines = 0;
        String[][] splitter = null;
        int currentLine = 0;

        try {
            File file = new File(path);
            java.io.FileReader fileReader = new java.io.FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            numberOfLines = Integer.parseInt(reader.readLine());

            splitter = new String[numberOfLines][MAX_NUMBER_OF_ELEMENTS_IN_LINE];

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
}
