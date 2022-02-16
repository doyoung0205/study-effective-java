package study.heejin.chapter2.item5;

public class SpellCheckerSingleton {
    private final Lexicon koreanDictionary = new KoreanDictionary();
    private final Lexicon englishDictionary = new EnglishDictionary();

    private SpellCheckerSingleton() {
    }

    private static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();

    public static SpellCheckerSingleton getInstance() {
        return INSTANCE;
    }

    public boolean isValidKorean(String word) {
        return koreanDictionary.isValid(word);
    }

    public boolean isValidEnglish(String word) {
        return englishDictionary.isValid(word);
    }
}
