import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int POSITION_OF_WAITING_TIME = 5;
    private String[][] dataLine = null;

    public boolean run(String path) {
        ReadFromFile fileReader = new ReadFromFile();
        String[][] splitter = fileReader.readFromFile(path);
        Parser parsing = new Parser();
        return parsing.parser(splitter);
    }

    public boolean parser(String[][] splitter) {
        dataLine = splitter;
        String[] currentDataLine;
        for (int i = 0; i < splitter.length; i++) {
            currentDataLine = splitter[i];
            String query = "D";
            if (isQuery(currentDataLine, query)) {
                calculateAverageWaitingTimeOfQuery(i, currentDataLine);
            }
        }
        return true;
    }

    private boolean calculateAverageWaitingTimeOfQuery(int currentQueryPosition,
                                                       String[] currentQuery) {
        int countQuestionOfQuery = 0;
        int sumOfWaitingTime = 0;

        String[] currentDataLine;

        for (int i = 0; i < currentQueryPosition; i++) {
            currentDataLine = dataLine[i];
            if (isDataLineRefersToQuery(currentQuery, currentDataLine)) {
                countQuestionOfQuery++;
                sumOfWaitingTime += Integer.parseInt(currentDataLine[POSITION_OF_WAITING_TIME]);
            }
        }

        calculateResult(countQuestionOfQuery, sumOfWaitingTime);
        return true;
    }

    private boolean isDataLineRefersToQuery(String[] currentQuery, String[] currentDataLine) {
        String waitingTimeLine = "C";
        return currentDataLine[0].equals(waitingTimeLine)
                && serviceCheck(currentQuery, currentDataLine)
                && questionType(currentQuery, currentDataLine)
                && dateCheck(currentQuery, currentDataLine);
    }

    private boolean dateCheck(String[] currentQuery, String[] currentDataLine) {
        String queryDateStr = currentQuery[currentQuery.length - 1];
        String timeLineDateStr = currentDataLine[currentDataLine.length - 2];

        LocalDate dataLineTime
                = LocalDate.parse(timeLineDateStr, FORMATTER);

        if (queryDateStr.contains("-")) {
            String[] dateGap = queryDateStr.split("-");
            LocalDate[] timeGap = {LocalDate.parse(dateGap[0], FORMATTER),
                    LocalDate.parse(dateGap[1], FORMATTER)};

            return !dataLineTime.isBefore(timeGap[0])
                    && !dataLineTime.isAfter(timeGap[1]);
        }

        LocalDate queryTime
                = LocalDate.parse(queryDateStr, FORMATTER);

        return queryTime.isEqual(dataLineTime);
    }

    private boolean serviceCheck(String[] currentQuery, String[] currentDataLine) {
        String anyMatch = "*";
        if (currentQuery[1].equals(anyMatch)) {
            return true;
        }

        String[] serviceOfQuery = currentQuery[1].split("\\.");
        String[] serviceOfDataLine = currentDataLine[1].split("\\.");

        if (serviceOfQuery.length > serviceOfDataLine.length) {
            return false;
        }

        for (int i = 0; i < serviceOfQuery.length; i++) {
            if (!serviceOfQuery[i].equals(serviceOfDataLine[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean questionType(String[] currentQuery, String[] currentDataLine) {
        if (currentQuery[2].equals("*")) {
            return true;
        }

        String[] questionTypeOfQuery = currentQuery[2].split("\\.");
        String[] questionTypeOfDataLine = currentDataLine[2].split("\\.");

        if (questionTypeOfQuery.length > questionTypeOfDataLine.length) {
            return false;
        }

        for (int i = 0; i < questionTypeOfQuery.length; i++) {
            if (!questionTypeOfQuery[i].equals(questionTypeOfDataLine[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isQuery(String[] currentOperation, String operationType) {
        return currentOperation[0].equals(operationType);
    }

    public String calculateResult(int count, int sum) {
        String out = null;
        if (count > 0) {
            out = Integer.toString(sum / count);
        } else {
            out = "-";
        }
        System.out.println(out);
        return out;
    }
}
