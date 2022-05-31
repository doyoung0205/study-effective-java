package chapter8.item56;

import java.util.Objects;

/**
 * packageName : chapter8.item56
 * fileName : User
 * author : haedoang
 * date : 2022-05-26
 * description :
 */
public class User {
    private Long id;
    private String name;
    private int age;

    private Nationality nationality;

    private User(String name, int age, Nationality nationality) {
        validate(name, age);
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * @param name 사용자의 이름
     * @param age  사용자의 나이
     * @throws NullPointerException if {@code name} is {@code null}
     * @throws IllegalArgumentException {@literal age < 1 }
     *
     * 이 메서드의 주석 내용은 {@index Effective Java Item56} 에 기술되어 있다.
     */
    private void validate(String name, int age) {
        if (Objects.isNull(name)) {
            throw new NullPointerException();
        }

        if (age < 1) {
            throw new IllegalArgumentException();
        }
    }

    public static User valueOf(String name, int age, Nationality nationality) {
        return new User(name, age, nationality);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Nationality getNationality() {
        return nationality;
    }
}
