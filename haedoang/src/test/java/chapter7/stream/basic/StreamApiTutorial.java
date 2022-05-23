package chapter7.stream.basic;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.item45
 * fileName : Tutorial
 * author : haedoang
 * date : 2022-05-03
 * description : <br/>
 * <a href="https://www.baeldung.com/java-8-streams">https://www.baeldung.com/java-8-streams</a>
 */
public class StreamApiTutorial {

    @Test
    @DisplayName("stream 생성하기")
    public void createStream() {
        // given
        Stream<Object> stream = Stream.empty();

        // then
        assertThat(stream.count()).isEqualTo(0);
        assertThat(stream).isNotNull()
                .as("요소가 없는 스트림의 null 반환을 피하기 위해 생성시 empty() 메서드를 사용한다");
    }


    @Test
    @DisplayName("모든 유형의 Collection 스트림을 생성할 수 있다")
    public void streamOfCollection() {
        // given
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Collection<String> collection2 = Sets.newHashSet("a", "b", "c");

        // when
        Stream<String> stream = collection.stream();
        Stream<String> stream1 = collection2.stream();

        // then
        assertThat(stream.collect(Collectors.joining())).isEqualTo(stream1.collect(Collectors.joining()));
    }

    @Test
    @DisplayName("배열도 스트림의 요소가 될 수 있다")
    public void streamOfArray() {
        // given
        Stream<String> streamOfArray = Stream.of("a", "b", "c");
        String[] arr = new String[]{"a", "b", "c"};

        // when
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamPart = Arrays.stream(arr, 0, 2);

        // then
        assertThat(streamOfArray.collect(Collectors.joining())).isEqualTo(stream.collect(Collectors.joining()));
        assertThat(streamPart.collect(Collectors.joining())).isEqualTo("ab");
    }

    @Test
    @DisplayName("스트림 빌더 타입으로 구현할 수 있다.")
    public void streamBuilder() {
        // given
        Stream<String> stream = Stream.<String>builder()
                .add("안")
                .add("녕")
                .add("하")
                .add("세")
                .add("요")
                .build();

        // then
        assertThat(stream.collect(Collectors.joining())).isEqualTo("안녕하세요");
    }

    @Test
    @DisplayName("generate() 메서드는 Supplier<T>를 허용하여 무한한 스트림을 생성하며 개발자가 크기를 지정해주어야 한다")
    public void streamGenerate() {
        // given
        Stream<String> streamGenerated =
                Stream.generate(() -> "헤이").limit(3);

        // then
        assertThat(streamGenerated.collect(Collectors.joining())).isEqualTo("헤이헤이헤이");
    }

    @Test
    @DisplayName("iterate()은 generate()와 같이 무한 스트림을 만들 수 있다")
    public void streamIterate() {
        // given
        Stream<Integer> streamIterate = Stream.iterate(1, n -> n + 1).limit(10);

        // then
        assertThat(streamIterate.reduce(0, Integer::sum)).isEqualTo(55);
    }

    @Test
    @DisplayName("java8은 int, long, double 기본 유형의 스트림을 생성할 수 있다. 또한, 제네릭과 함꼐 유형 매개변수로 사용할 수 없다")
    public void streamOfPrimitives() {
        // given
        IntStream intStream = IntStream.range(1, 5);
        LongStream longStream = LongStream.rangeClosed(1, 3);
        DoubleStream doubleStream = DoubleStream.generate(Math::random).limit(5);

        // then
        assertThat(intStream.sum()).isEqualTo(10);
        assertThat(longStream.sum()).isEqualTo(6);
        assertThat(doubleStream.sum()).isGreaterThan(Double.MIN_VALUE);
    }

    @Test
    @DisplayName("String 클래스의 chars() 메서드를 사용하여 IntStream으로 변환할 수 있다")
    public void streamOfString() {
        // given
        IntStream streamOfChars = "ABC".chars();

        // when
        String actual = streamOfChars.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        // then
        assertThat(actual).isEqualTo("ABC");
    }


    @Test
    @DisplayName("java nio class의 Files의 lines() 메서드를 통해 Stream<String>을 생성할 수 있다")
    public void streamOfFile() throws IOException {
        // given
        Path path = new File("src/main/resources/file.txt").toPath();

        // when
        Stream<String> streamOfStrings = Files.lines(path);

        // then
        assertThat(streamOfStrings.collect(Collectors.joining())).isEqualTo("두아리파");
    }

    //TODO Referencing a Stream
    //https://www.baeldung.com/java-8-streams

}
