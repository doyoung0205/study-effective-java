package chapter5.item26;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/03/19
 * description :
 */
public class Member {
    private final String name;

    private Member(String name) {
        this.name = name;
    }

    public static Member valueOf(String name) {
        return new Member(name);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
