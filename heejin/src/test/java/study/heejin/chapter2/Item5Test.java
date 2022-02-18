package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item5.*;
import study.heejin.chapter2.item5.factorymethod.*;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static study.heejin.chapter2.item5.factorymethod.TilePattern.*;

class Item5Test {

    @Test
    @DisplayName("정적 유틸리티 클래 테스트")
    void dictionaryUtility() {
        boolean isValidKorean = SpellCheckerUtility.isValidKorean("사과");
        boolean isValidEnglish = SpellCheckerUtility.isValidEnglish("Apple");

        assertThat(isValidKorean).isTrue();
        assertThat(isValidEnglish).isTrue();
    }

    @Test
    @DisplayName("싱글턴 클래스 테스트")
    void dictionarySingleton() {
        SpellCheckerSingleton singletonSpellChecker = SpellCheckerSingleton.getInstance();

        boolean isValidKorean = singletonSpellChecker.isValidKorean("사과");
        boolean isValidEnglish = singletonSpellChecker.isValidEnglish("Apple");

        assertThat(isValidKorean).isTrue();
        assertThat(isValidEnglish).isTrue();
    }

    @Test
    @DisplayName("의존 관계 주입 클래스 테스트")
    void dictionaryDI() {
        SpellCheckerDI koreanDictionary = new SpellCheckerDI(new KoreanDictionary());
        SpellCheckerDI englishDictionary = new SpellCheckerDI(new EnglishDictionary());

        boolean isValidKorean = koreanDictionary.isValid("사과");
        boolean isValidEnglish = englishDictionary.isValid("Apple");

        assertThat(isValidKorean).isTrue();
        assertThat(isValidEnglish).isTrue();
    }

    @Test
    @DisplayName("생성자에 자원 팩터리를 넘겨주는 방식 테스트")
    void factoryMethodDI() {
        int size = 100;
        int width = 10;
        Mosaic mosaic = new Mosaic(size);

        for (int i = 0; i <= size; i++) {
            if (odd(i)) {
                mosaic.add(() -> getWhiteTile(BACKGROUND));
            } else {
                mosaic.add(() -> getYellowTile(TRADITIONAL));
            }
        }
        printMosaic(mosaic, size, width);

        SimpleMosaic simpleMosaic = new SimpleMosaic(() -> getYellowTile(NORDIC));
        System.out.println("*** " + simpleMosaic.getTilePattern());
    }

    private void printMosaic(Mosaic mosaic, int size, int width) {
        List<Supplier<? extends Tile>> tiles = mosaic.getMosaic();

        for (int i = 1; i < size + 1; i++) {
            Tile tile = tiles.get(i).get();
            System.out.printf(" [%s] ", tile.getTilePattern());
            if (i % width == 0) {
                System.out.println();
            }
        }
    }

    private boolean odd(int index) {
        return (index + 1) % 2 == 0;
    }

    private YellowTile getYellowTile(TilePattern tilePattern) {
        return new YellowTile.Builder()
                .addTile(tilePattern)
                .build();
    }

    private WhiteTile getWhiteTile(TilePattern tilePattern) {
        return new WhiteTile.Builder()
                .addTile(tilePattern)
                .build();
    }
}
