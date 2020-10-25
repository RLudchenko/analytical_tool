import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
    private final Parser parser = new Parser();
    private final String PATH = "src\\main\\resources\\data.txt";
    private final ReadFromFile fileReader = new ReadFromFile();
    String[][] splitter = fileReader.readFromFile(PATH);

    @Test
    public void pathTest() {
        Assert.assertTrue(parser.run(PATH));
    }

    @Test
    public void parserTest() {
        Assert.assertTrue(parser.parser(splitter));
    }

    @Test
    public void regularResultTest() {
        Assert.assertEquals("83", parser.calculateResult(1, 83));
    }

    @Test
    public void secondRegularTest() {
        Assert.assertEquals("100", parser.calculateResult(2, 200));
    }

    @Test
    public void lessThanZeroTest() {
        Assert.assertEquals("-", parser.calculateResult(0, 0));
    }
}
