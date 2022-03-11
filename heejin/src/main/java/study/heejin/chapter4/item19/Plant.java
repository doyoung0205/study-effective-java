package study.heejin.chapter4.item19;

public class Plant {
    private String name;
    private String family;

    public Plant(String name, String family) {
        this.name = name;
        this.family = family;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec 식물이 속한 과가 피는 계절을 조회한다.
     */
    protected String season(String family) {
        return family;
    }
}
