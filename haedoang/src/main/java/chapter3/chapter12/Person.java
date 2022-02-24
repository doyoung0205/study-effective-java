package chapter3.chapter12;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/02/22
 * description :
 */
public class Person {
    private String name;
    private int age;
    private String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(job, person.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, job);
    }
}
