import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    public static final String CALCULATE_ERROR_EXCEPTION_MSG = "Calculate Error";

    public String getResult(String inputStr) {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);
                sortWordInfoList(wordInfoList);
                StringJoiner wordsJoiner = new StringJoiner("\n");

                for (WordInfo wordInfo : wordInfoList) {
                    String wordInfoLine = wordInfo.getWord() + " " + wordInfo.getWordCount();
                    wordsJoiner.add(wordInfoLine);
                }
                return wordsJoiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR_EXCEPTION_MSG;
            }
        }

    private void sortWordInfoList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
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
