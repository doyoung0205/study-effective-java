package study.heejin.chapter2.item2;

public class NutritionFacts_1 {
    private final int servingSize;      // (ml, 1회 제공량)     필수
    private final int servings;         // (회, 총 n회 제공량)   필수
    private final int calories;         // (1회 제공량)         선택
    private final int fat;              // (g/1회 제공량)       선택
    private final int sodium;           // (mg/1회 제공량)      선택
    private final int carbohydrate;     // (g/1회 제공량)       선택

    public NutritionFacts_1(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts_1(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts_1(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts_1(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts_1(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
