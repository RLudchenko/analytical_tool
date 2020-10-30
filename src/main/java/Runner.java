import java.io.File;

public class Runner {
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.run("src\\main\\resources\\Data.txt".replaceAll("\\{2}", File.separator));
    }
}
