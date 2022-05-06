package chapter7.item47;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class Item47Test {

    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl();
    }

    @Test
    public void test() {
        personService = new PersonServiceImpl();
    }

    @Test
    @DisplayName("스트림을 반환하는 메서드를 반복하는 방법")
    public void streamResult() {
        // given
        final Stream<Person> persons = personService.persons();
        List<Person> personList = new ArrayList<>();

        // when
        for (Person person : StreamUtils.iterableOf(persons)) {
            personList.add(person);
        }

        // then
        assertThat(personList).hasSize(4)
                .as("반복을 위한 중계 어댑터가 필요하다");
    }

    @Test
    @DisplayName("멱집합 테스트")
    public void powerSetTest() {
        // given
        final ArrayList<String> strings = Lists.newArrayList("a", "b", "c");
        final Collection<Set<String>> powerSet = PowerSet.of(Sets.newHashSet(strings));

        // then
        assertThat(powerSet).hasSize(8);

        // when
        final Collection<List<String>> subList = SubLists.of(strings)
                .collect(Collectors.toList());

        // then
        assertThat(subList).contains(
                Collections.emptyList(),
                List.of("a"),
                List.of("b"),
                List.of("c"),
                List.of("a", "b"),
                List.of("a", "b", "c"),
                List.of("b", "c")
        );
    }
}
