import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) throws IOException {
        List<String> query = new ArrayList<>();
        List<String> waitingTime = new ArrayList<>();

        try {
            String text = Files.readString(Paths.get("D:\\analytical_tool\\data.txt"));
            Scanner myReader = new Scanner(text);

            List<String> result = new ArrayList<>();

            while (myReader.hasNextLine()) {
                result.add(myReader.nextLine());
            }

            for (String s : result) {
                if (s.startsWith("D")) {
                    query.add(s);
                }

                if (s.startsWith("C")) {
                    waitingTime.add(s);
                }
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.!");
        }
    }
}
