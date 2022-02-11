package study.heejin.chapter2.item1;

public enum CoffeeBean {
    Kenya("케냐"),
    Brazil("브라질"),
    Ethiopia("에티오피아"),
    Colombia("콜롬비아");

    private String origin;

    CoffeeBean(String origin) {
        this.origin = origin;
    }
}
