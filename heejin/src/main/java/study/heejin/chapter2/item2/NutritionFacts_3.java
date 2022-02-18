package study.heejin.chapter2.item2;

public class NutritionFacts_3 {
    private final int servingSize;      // (ml, 1회 제공량)     필수
    private final int servings;         // (회, 총 n회 제공량)   필수
    private final int calories;         // (1회 제공량)         선택
    private final int fat;              // (g/1회 제공량)       선택
    private final int sodium;           // (mg/1회 제공량)      선택
    private final int carbohydrate;     // (g/1회 제공량)       선택

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수
        private int calories     = 0;
        private int fat          = 0;
        private int sodium       = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts_3 build() {
            return new NutritionFacts_3(this);
        }
    }

    public NutritionFacts_3(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }
}
