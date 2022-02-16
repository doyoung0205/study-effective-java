package study.heejin.chapter2.item5;

public class SpellCheckerUtility {

    private static final Lexicon koreanDictionary = new KoreanDictionary();
    private static final Lexicon englishDictionary = new EnglishDictionary();

    private SpellCheckerUtility() {
    }

    public static boolean isValidKorean(String word) {
        return koreanDictionary.isValid(word);
    }

    public static boolean isValidEnglish(String word) {
        return englishDictionary.isValid(word);
    }
}
