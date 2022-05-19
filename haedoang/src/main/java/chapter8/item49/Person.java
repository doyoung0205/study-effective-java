package chapter8.item49;

import java.util.Objects;

/**
 * packageName : chapter8
 * fileName : Person
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class Person {
    private final String name;
    private final int age;

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     *
     * @param name 이름 null 값은 올 수 없다
     * @param age 나이
     * @return
     * @Throws NullPointerException 이름이 null일 경우 발생한다
     */
    public static Person valueOf(String name, int age) {
        return new Person(Objects.requireNonNull(name), age);
    }
}
