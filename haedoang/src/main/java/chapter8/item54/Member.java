package chapter8.item54;

/**
 * packageName : chapter8.item54
 * fileName : Member
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Member {
    private final String name;
    private final int age;

    private Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public static Member valueOf(String name, int age) {
        return new Member(name, age);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
