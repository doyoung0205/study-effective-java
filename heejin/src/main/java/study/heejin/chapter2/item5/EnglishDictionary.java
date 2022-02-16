package study.heejin.chapter2.item5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishDictionary implements Lexicon {

    private static final Pattern ENGLISH_PATTERN = Pattern.compile("^[a-zA-Z]*$");

    @Override
    public boolean isValid(String word) {
        Matcher matcher = ENGLISH_PATTERN.matcher(word);
        return matcher.find();
    }

    @Override
    public String type() {
        return "영어 사전";
    }
}
