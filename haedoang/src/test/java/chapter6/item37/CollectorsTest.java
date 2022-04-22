package chapter6.item37;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class CollectorsTest {

    @Test
    @DisplayName("groupingBy는 function input값을 기준으로 Map의 키를 만든다. value는 list 컬렉션으로 반환된다")
    public void groupingByStringTest() {
        // given
        final ArrayList<String> words = Lists.newArrayList("A", "B", "C", "D");

        // when
        final Map<String, List<String>> collect = words.stream().collect(Collectors.groupingBy(it -> it));

        // then
        assertThat(collect.get("A")).contains("A").hasSize(1);
        assertThat(collect.get("B")).contains("B").hasSize(1);
        assertThat(collect.get("C")).contains("C").hasSize(1);
        assertThat(collect.get("D")).contains("D").hasSize(1);
    }

    @Test
    @DisplayName("Object 매핑 grouppingBy Test")
    public void groupingByObjectTest() {
        // given
        final ArrayList<Person> duplicatedList = Lists.newArrayList(
                new Person("김해동", 34),
                new Person("김김김", 31),
                new Person("김해동", 32),
                new Person("김해동", 34)
        );

        // when
        final Map<String, List<Person>> nameListMap = duplicatedList.stream().collect(Collectors.groupingBy(Person::getName));

        // then
        assertThat(nameListMap.get("김김김")).extracting(Person::getAge).contains(31).hasSize(1);
        assertThat(nameListMap.get("김해동")).extracting(Person::getAge).contains(34, 32, 34).hasSize(3);

        // when except duplicate
        final Map<String, Set<Person>> nameSetMap = duplicatedList.stream().collect(Collectors.groupingBy(Person::getName, toSet()));

        // then
        assertThat(nameSetMap.get("김김김")).extracting(Person::getAge).contains(31).hasSize(1);
        assertThat(nameSetMap.get("김해동")).extracting(Person::getAge).contains(34, 32).hasSize(2);
    }

    @Test
    @DisplayName("mergeFunction을 중복데이터를 처리한다")
    public void toMapMergeFunctionTest() {
        // given
        final ArrayList<Person> duplicatedList = Lists.newArrayList(
                new Person("김해동", 34),
                new Person("김김김", 31),
                new Person("김해동", 32),
                new Person("김해동", 34)
        );

        // when
        final Map<Integer, String> actual = duplicatedList.stream()
                .collect(toMap(it -> it.getAge(), it -> it.getName(), (x, y) -> x + ":" + y));


        // then
        assertThat(actual.get(34)).isEqualTo("김해동:김해동");
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
