import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
    private final Parser parser = new Parser();
    private final String PATH = "src\\main\\resources\\Data.txt";
    private final String MOCK_PATH = "src\\main\\resources\\MockData.txt";
    private final ReadFromFile fileReader = new ReadFromFile();
    private final ReadFromFile mockFileReader = new ReadFromFile();
    String[][] splitter = fileReader.readFromFile(PATH);
    String[][] mockSplitter = fileReader.readFromFile(MOCK_PATH);

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
    
    @Test
    public void readFromMockDataTest() { 
        mockSplitter = mockFileReader.readFromFile(MOCK_PATH);
        Assert.assertTrue(parser.parser(mockSplitter));
    }
}
