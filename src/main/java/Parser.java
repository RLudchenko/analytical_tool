import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) throws IOException {
        List<String> query = new ArrayList<>();
        List<String> waitingTimeLine = new ArrayList<>();
        String checkQuery = "";
        String checkWaitingTimeLine = "";

        try {
            String text = Files.readString(Paths.get("D:\\analytical_tool\\data.txt"));
            Scanner myReader = new Scanner(text);

            List<String[]> result = new ArrayList<>();

            try {

                File file = new File("D:\\analytical_tool\\data.txt");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();

                while (line != null) {
                    line = reader.readLine();
                }

                while (myReader.hasNextLine()) {
                    result.add(myReader.nextLine().split(" "));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

//            for (String s : result) {
//                if (s.startsWith("D")) {
//                    query.add(s);
//                }
//
//                if (s.startsWith("C")) {
//                    waitingTimeLine.add(s);
//                }
//            }

            List<String> updatedQuery = new ArrayList<>();
            List<String> updatedWaitingTimeLine = new ArrayList<>();

            for (String s : query) {
                checkQuery = s.substring(s.indexOf(' ') + 1);
                updatedQuery.add(checkQuery);
            }

            for (String w : waitingTimeLine) {
                checkWaitingTimeLine = w.substring(w.indexOf(' ') + 1);
                updatedWaitingTimeLine.add(checkWaitingTimeLine);
            }

            for (String s : updatedQuery) {
                System.out.println(s);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.!");
        }
    }
}
