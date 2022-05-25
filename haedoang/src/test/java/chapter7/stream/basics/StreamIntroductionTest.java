package chapter7.stream.basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.stream.basics
 * fileName : StreamIntroduction
 * author : haedoang
 * date : 2022-05-19
 * description : <br/>
 * <a href="https://www.baeldung.com/java-8-streams-introduction">java-8-streams-introduction</a>
 */
public class StreamIntroductionTest {

    @Test
    @DisplayName("스트림 생성하기")
    public void create() {
        // given
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> arrayStream = Arrays.stream(arr);
        Stream<String> listStream = Arrays.asList("a", "b", "c").stream();
        Stream<String> stream = Stream.of("a", "b", "c");

        // when
        String arrayStreamResult = arrayStream.collect(Collectors.joining(","));
        String listStreamResult = listStream.collect(Collectors.joining(","));
        String streamResult = stream.collect(Collectors.joining(","));

        // then
        assertThat(arrayStreamResult)
                .isEqualTo(listStreamResult)
                .isEqualTo(streamResult)
                .as("모든 컬렉션 요소 소스로 Stream 을 만들 수 있다");
    }

    @RepeatedTest(50)
    @DisplayName("멀티스레드 스트림")
    public void multiThreadStream() throws InterruptedException {
        // given
        Set<String> threadNames = new HashSet<>();

        // when
        Arrays.asList("a", "b", "c", "d", "e")
                .parallelStream()
                .forEach(it -> {
                    it.toUpperCase();
                    threadNames.add(Thread.currentThread().getName());
                });

        // then
        Thread.sleep(500);
        assertThat(threadNames.size()).isGreaterThan(1)
                .as("병렬 스트림 사용에 주의해야 할 것. 메인스트림이 먼저 끝나는 경우");
    }


    @Test
    @DisplayName("중간 연산의 연결과 소스를 변경하지 않는 스트림")
    public void chainingOperationsAndTerminalOperations() {
        // given
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");

        // when
        long actual = list.stream()
                .distinct()
                .count();

        // then
        assertThat(list).hasSize(5);
        assertThat(list).hasSize((int) actual)
                .as("distinct() 중간 연산은 메서드 체이닝을 지원한다")
                .as("count() 종단 연산은 터미널 작업으로 결과 타입을 반환한다");
    }


    @Test
    @DisplayName("for-each, while을 대체하는 stream")
    public void iterating() {
        // given
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        boolean result = false;

        // when
        for (String str : list) {
            if (str.contains("a")) {
                result = true;
            }
        }

        // then
        assertThat(result).isTrue();

        // when
        boolean actual = list.stream()
                .anyMatch(it -> it.contains("a"));

        // then
        assertThat(actual).isTrue().isEqualTo(result)
                .as("stream 의 반복 사용하기");
    }


    @Test
    @DisplayName("'d' 가 들어간 데이터 필터하기")
    public void filtering() {
        // given
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("OneAndOnly");
        list.add("Derek");
        list.add("Change");
        list.add("factory");
        list.add("justBefore");
        list.add("Italy");
        list.add("Italy");
        list.add("Thursday");
        list.add("");
        list.add("");

        // when
        List<String> filteredList = list.stream().filter(it -> it.contains("d")).collect(Collectors.toList());

        // then
        assertThat(filteredList).hasSize(2);
    }

    @Test
    @DisplayName("새 요소를 Stream으로 수집하는 map")
    public void mapping() {
        // given
        List<String> uris = new ArrayList<>();
        uris.add("file.txt");
        uris.add("file_copy.txt");

        // when
        Stream<Path> stream = uris.stream().map(Paths::get);

        // then
        assertThat(stream.collect(Collectors.toList())).hasSize(2);
    }

    class Person {
        private String name;
        private int age;

        private List<String> items;

        public Person(String name, int age, List<String> items) {
            this.name = name;
            this.age = age;
            this.items = new ArrayList<>(items);
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public List<String> getItems() {
            return items;
        }
    }

    @Test
    @DisplayName("flatMap 사용 테스트")
    public void mapping2() {
        // given
        List<Person> people =
                Arrays.asList(
                        new Person("haedoang", 34, Arrays.asList("강아지", "고양이")),
                        new Person("nana", 27, Arrays.asList("짜장면", "짬뽕")),
                        new Person("chew", 23, Arrays.asList("신발", "마이크"))
                );

        // when
        Stream<String> actual = people.stream()
                .flatMap(it -> it.getItems().stream());

        // then
        assertThat(actual).hasSize(6);
        assertThat(actual).containsExactly("강아지", "고양이", "짜장면", "짬뽕", "신발", "마이크");
    }


    @Test
    @DisplayName("anyMatch(), allMatch(), noneMatch()")
    public void matching() {
        // given
        List<Person> people =
                Arrays.asList(
                        new Person("haedoang", 34, Arrays.asList("강아지", "고양이")),
                        new Person("nana", 27, Arrays.asList("짜장면", "짬뽕")),
                        new Person("chew", 23, Arrays.asList("신발", "마이크"))
                );

        // when
        boolean hasAnyPuppy = people.stream().anyMatch(it -> it.items.contains("강아지"));
        boolean areAdult = people.stream().allMatch(it -> it.getAge() > 20);
        boolean areYouth = people.stream().noneMatch(it -> it.getAge() > 40);

        // then
        assertThat(hasAnyPuppy).isTrue();
        assertThat(areAdult).isTrue();
        assertThat(areYouth).isTrue();

        // then
        assertThat(Stream.empty().allMatch(Objects::nonNull)).isTrue();
        assertThat(Stream.empty().anyMatch(Objects::nonNull)).isFalse();
    }


    @Test
    @DisplayName("요소 시퀀스를 일부 값으로 줄이기")
    public void reduction() {
        // when
        Integer sum = Stream.iterate(1, i -> i + 1).limit(10)
                .reduce(0, Integer::sum);

        // then
        assertThat(sum).isEqualTo(55);
    }


    @Test
    @DisplayName("수집기")
    public void collecting() {
        // given
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");

        // when
        List<String> actual = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsExactly("A", "B", "C", "D", "E");
    }
}


