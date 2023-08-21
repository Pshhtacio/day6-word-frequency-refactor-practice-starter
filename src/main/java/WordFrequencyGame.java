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

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String s : words) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(s, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> frequencyInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    frequencyInfos.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = frequencyInfos;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                .collect(Collectors.joining(NEWLINE_DELIMITER));
    }


    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();

        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            map.computeIfAbsent(wordFrequencyInfo.getWord(), k -> new ArrayList<>()).add(wordFrequencyInfo);
        }

        return map;
    }


}
