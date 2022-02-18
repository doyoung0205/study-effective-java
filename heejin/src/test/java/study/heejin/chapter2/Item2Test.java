package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item2.*;

import static study.heejin.chapter2.item2.NyPizza.Size.SMALL;
import static study.heejin.chapter2.item2.Pizza.Topping.*;

class Item2Test {

    @Test
    @DisplayName("점층정 생성자 패턴 객체 생성 테스트")
    void nutritionFacts_1() {
        NutritionFacts_1 cocaCola = new NutritionFacts_1(240, 8, 100, 0, 35, 27);
    }

    @Test
    @DisplayName("자바 빈즈 패턴 객체 생성 테스트")
    void nutritionFacts_2() {
        NutritionFacts_2 cocaCola = new NutritionFacts_2();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }

    @Test
    @DisplayName("빌더 패턴 객체 생성 테스트")
    void nutritionFacts_3() {
        NutritionFacts_3 nutritionFacts_3 = new NutritionFacts_3.Builder(240, 8)
                                                .calories(100)
                                                .sodium(35)
                                                .carbohydrate(27)
                                                .build();
    }

    @Test
    @DisplayName("계층형 클래스에서 빌더 패턴 사용")
    void builderPatternPizza() {
        NyPizza nyPizza = new NyPizza.Builder(SMALL)
                                .addTopping(SAUSAGE)
                                .addTopping(ONION)
                                .build();

        Calzone calzone = new Calzone.Builder()
                                .addTopping(HAM)
                                .sauceInside()
                                .build();
    }
}
