import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = Arrays.stream(words)
                        .map(word -> new WordFrequencyInfo(word, 1))
                        .collect(Collectors.toList());

                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);
                wordFrequencyInfoList = wordFrequencyMap.entrySet().stream()
                        .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());

                return wordFrequencyInfoList.stream()
                        .sorted(Comparator.comparingInt(WordFrequencyInfo::getWordCount).reversed())
                        .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                        .collect(Collectors.joining(NEWLINE_DELIMITER));

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();

        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            map.computeIfAbsent(wordFrequencyInfo.getWord(), k -> new ArrayList<>()).add(wordFrequencyInfo);
        }

        return map;
    }


}
