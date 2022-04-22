package chapter6.item40;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Sports {
    private String name;

    private Sports(String name) {
        this.name = name;
    }

    public static Sports valueOf(String name) {
        return new Sports(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sports sports = (Sports) o;
        return Objects.equals(name, sports.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
