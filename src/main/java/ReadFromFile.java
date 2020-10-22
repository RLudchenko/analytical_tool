import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ReadFromFile {
    public static String[][] readFromFile(String path) {
        String line = "";

        String[][] splitter = new String[7][6];
        int currentLine = 0;

        try {
            File file = new File(path);
            java.io.FileReader fileReader = new java.io.FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

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
}
