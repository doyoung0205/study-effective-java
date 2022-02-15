package study.heejin.chapter2.item2;

public class NutritionFacts_2 {
    private int servingSize     = -1;     // (ml, 1회 제공량)       필수
    private int servings        = -1;     // (회, 총 n회 제공량)     필수
    private int calories        = 0;      // (1회 제공량)           선택
    private int fat             = 0;      // (g/1회 제공량)         선택
    private int sodium          = 0;      // (mg/1회 제공량)        선택
    private int carbohydrate    = 0;      // (g/1회 제공량)         선택

    public NutritionFacts_2() {
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
