import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    public static final String CALCULATE_ERROR_EXCEPTION_MSG = "Calculate Error";


    public String getWordFrequencyResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
            return wordInfoList.stream()
                    .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
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
        List<WordInfo> wordInfos = new ArrayList<>();

        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, frequency);
            wordInfos.add(wordInfo);
        });

        return wordInfos;
    }
}
