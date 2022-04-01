package chapter5.item30;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
@DisplayName("Function test")
public class FunctionTest {
    @Test
    @DisplayName("리스트를 map으로 변환하기")
    public void listToMap() {
        // given
        final ArrayList<Person> people = Lists.newArrayList(Person.valueOf("김해동", 34), Person.valueOf("v", 28), Person.valueOf("rm", 25));

        // when
        final Map<String, Integer> personMap = people.stream().collect(Collectors.toMap(Person::getName, Person::getAge));


        // then
        assertThat(personMap.get("김해동")).isEqualTo(34);
    }

    @Test
    @DisplayName("인수를 반환하는 메서드인 Function.identity() 테스트")
    public void identityMethod() {
        // given
        final ArrayList<Person> people = Lists.newArrayList(
                Person.valueOf("김해동", 34),
                Person.valueOf("v", 28),
                Person.valueOf("rm", 25)
        );

        // when
        final Map<String, Person> nameKeyMap = people.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity()));

        // then
        assertThat(nameKeyMap.get("김해동")).isEqualTo(Person.valueOf("김해동", 34));
    }
}


class Person {
    private final String name;
    private final int age;

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static Person valueOf(String name, int age) {
        return new Person(name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() && Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }
}