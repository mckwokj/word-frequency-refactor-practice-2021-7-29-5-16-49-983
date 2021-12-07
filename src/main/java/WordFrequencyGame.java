import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    public static final String CALCULATE_ERROR_EXCEPTION_MSG = "Calculate Error";


    public String getWordFrequencyResult(String sentence) {
        try {
            List<WordInfo> wordInfos = calculateWordFrequency(sentence);
            return wordInfos.stream()
                    .sorted(Comparator.comparingInt(WordInfo::getWordCount).reversed())
                    .map(wordInfo -> getWordInfoLine(wordInfo))
                    .collect(Collectors.joining()).trim();
        } catch (Exception e) {
            return CALCULATE_ERROR_EXCEPTION_MSG;
        }
    }

    private String getWordInfoLine(WordInfo wordInfo) {
        return wordInfo.getWord() + " " + wordInfo.getWordCount() + "\n";
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        return distinctWords.stream()
                .map(word -> new WordInfo(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}
