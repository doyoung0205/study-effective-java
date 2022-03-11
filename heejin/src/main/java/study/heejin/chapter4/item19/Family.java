package study.heejin.chapter4.item19;

public enum Family {
    Liliaceae("백합과", "6월 중순～7월 초순"),
    Rosaceae("백합과", "6월 중순～7월 초순"),
    Asteraceae("국화과", "5월");

    private final String name;
    private final String season;

    Family(String name, String season) {
        this.name = name;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public String getSeason() {
        return season;
    }
}
