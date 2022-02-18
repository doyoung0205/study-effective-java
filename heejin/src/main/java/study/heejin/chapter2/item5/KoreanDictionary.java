package study.heejin.chapter2.item5;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KoreanDictionary implements Lexicon {

    private static final Pattern KOREAN_PATTERN = Pattern.compile("^[가-힣]*$");

    @Override
    public boolean isValid(String word) {
        Matcher matcher = KOREAN_PATTERN.matcher(word);
        return matcher.find();
    }

    @Override
    public String type() {
        return "한국어 사전";
    }
}
