import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final int POSITION_OF_WAITING_TIME = 5;
    private final String waitingTimeLine = "C";
    private final String query = "D";
    private final String anyMatch = "*";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[][] dataLine = null;

    public boolean run(String path) {
        ReadFromFile fileReader = new ReadFromFile();
        String[][] splitter = fileReader.readFromFile(path);
        Parser parser = new Parser();
        return parser.parser(splitter);
    }

    private boolean parser(String[][] splitter) {
        dataLine = splitter;
        String[] currentDataLine;
        for(int i = 0; i < splitter.length; i++) {
            currentDataLine = splitter[i];
            if (isQuery(currentDataLine, "D")) {
                calculateAverageWaitingTimeOfQuery(i, currentDataLine);
            }
        }
        return true;
    }

    private boolean calculateAverageWaitingTimeOfQuery(int currentQueryPosition, String[] currentQuery) {
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
        return  currentDataLine[0].equals("C")
                && categoryCheck(currentQuery, currentDataLine)
                && dataCheck(currentQuery, currentDataLine);
    }

    private boolean dataCheck(String[] currentQuery, String[] currentDataLine) {
        String queryDate = currentQuery[currentQuery.length - 1];
        String timeLineDate = currentDataLine[currentDataLine.length - 2];

        if (queryDate.contains("-")) {
            String[] dateGap = queryDate.split("-");

            LocalDate timeLineTime
                    = LocalDate.parse(timeLineDate, FORMATTER);
            LocalDate[] timeGap = {LocalDate.parse(dateGap[0], FORMATTER),
                    LocalDate.parse(dateGap[1], FORMATTER)};

            return !timeLineTime.isBefore(timeGap[0])
                    && !timeLineTime.isAfter(timeGap[1]);
        }


        LocalDate regularWaitingTimeLine
                = LocalDate.parse(timeLineDate, FORMATTER);
        LocalDate regularQueryTimeLine
                = LocalDate.parse(queryDate, FORMATTER);

        return regularQueryTimeLine.isEqual(regularWaitingTimeLine);
    }

    private boolean categoryCheck(String[] currentQuery, String[] currentDataLine) {
        return true;
    }


    private boolean isQuery(String[] currentOperation, String operationType) {
        return currentOperation[0].equals(operationType);
    }

//    private void parserht(String[][] splitter) {
//        for (int i = 0; i < splitter.length; i++) {
//            int sum = 0;
//            int count = 0;
//            if (splitter[i][0].contains(query)) {
//                queryCompare:
//                for (int j = 0; j < i; j++) {
//                    if (!splitter[j][0].contains(waitingTimeLine)) {
//                        continue;
//                    }
//
//                    for (int k = 1; k < splitter[i].length - 2; k++) {
//                        if (splitter[j][k].equals("anyMatch") || splitter[i][k].equals("anyMatch")) {
//                            continue;
//                        }
//
//                        if (!splitter[j][k].matches("^" + splitter[i][k] + "(.*)")) {
//                            continue queryCompare;
//                        }
//                    }
//
//                    String queryDate = splitter[i][splitter[i].length - 1];
//                    if (queryDate.contains("-")) {
//                        String[] queryDateGap = queryDate.split("-");
//
//                        LocalDate[] timeGap = { LocalDate.parse(queryDateGap[0], FORMATTER),
//                                LocalDate.parse(queryDateGap[1], FORMATTER)};
//                        LocalDate timeLineTime
//                                = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);
//
//                        if (timeLineTime.isBefore(timeGap[0]) || timeLineTime.isAfter(timeGap[1])) {
//                            continue;
//                        }
//                    } else {
//                        LocalDate queryTime
//                                = LocalDate.parse(queryDate, FORMATTER);
//                        LocalDate timeLineTime
//                                = LocalDate.parse(splitter[j][splitter[j].length - 2], FORMATTER);
//
//                        if (!queryTime.isEqual(timeLineTime)) {
//                            continue;
//                        }
//                    }
//
//                    sum += Integer.parseInt(splitter[j][splitter[j].length - 1]);
//                    count++;
//                }
//
//                calculateResult(count, sum);
//            }
//        }
//    }

    private void calculateResult(int count, int sum) {
        if (count > 0) {
            System.out.println(sum / count);
        } else {
            System.out.println("-");
        }
    }
}
