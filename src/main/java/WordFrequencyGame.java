import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + SPACE_CHAR + "1";
        } else {
            try {
                String[] words = inputStr.split(SPACE_DELIMITER);

                Map<String, Long> wordFrequencyMap = Arrays.stream(words)
                        .collect(Collectors.groupingBy(
                                word -> word,
                                Collectors.counting()
                        ));

                return wordFrequencyMap.entrySet().stream()
                        .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().intValue()))
                        .sorted(Comparator.comparingInt(WordFrequencyInfo::getWordCount).reversed())
                        .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                        .collect(Collectors.joining(NEWLINE_DELIMITER));

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream().collect(Collectors.groupingBy(WordFrequencyInfo::getWord));
    }


}
