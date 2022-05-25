package chapter8.optional;

import java.util.Optional;

/**
 * packageName : chapter8.optional
 * fileName : Person
 * author : haedoang
 * date : 2022-05-25
 * description :
 */
public class Person {
    private String name;
    private int age;
    private String password;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }
}
