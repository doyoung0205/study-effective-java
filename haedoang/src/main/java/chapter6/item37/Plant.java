package chapter6.item37;

/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class Plant {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
