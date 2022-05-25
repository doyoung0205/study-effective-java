package chapter7.stream.basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.stream.basics
 * fileName : FindFirstVersusFindAny
 * author : haedoang
 * date : 2022-05-19
 * description : <br/>
 * <a href="https://www.baeldung.com/java-stream-findfirst-vs-findany">java-stream-findfirst-vs-findany</a>
 */
public class FindFirstVersusFindAnyTest {

    @Test
    @DisplayName("Stream 요소 중 무작위로 반환한다")
    public void findAny() {
        // given
        List<String> list = Arrays.asList("A", "B", "C", "D");

        // when
        Optional<String> actual = list.stream().findAny();

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isIn("A", "B", "C", "D");
    }


    @Test
    @DisplayName("Stream 요소 중 첫번째 요소를 반환한다")
    public void findFirst() {
        // given
        List<String> list = Arrays.asList("A", "B", "C", "D");

        // when
        Optional<String> actual = list.stream().findFirst();

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo("A");
    }
}
