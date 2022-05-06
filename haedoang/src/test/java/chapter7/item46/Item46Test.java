package chapter7.item46;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class Item46Test {

    @Test
    @DisplayName("forEach문에서 외부 상태를 수행하는 표현은 스트림을 제대로 사용하는 표현이 아니다")
    public void badExpressionInStream() {
        // given
        final HashMap<String, Long> freq = new HashMap<>();

        // when
        try (Stream<String> words = Stream.of("a", "b", "c", "d", "e")) {
            words.forEach(word -> {
                freq.merge(word.toUpperCase(), 1L, Long::sum);
            });
        }

        // then
        assertThat(freq).hasSize(5);
        assertThat(freq.keySet()).containsExactly("A", "B", "C", "D", "E")
                .as("종단 연산을 수행하는  forEach 문이 외부 상태를 수정하는 역할까지 담당하게 되었다")
                .as("forEach문은 스트림 계산 결과를 보고할 때만 사용하고 계산할 떄는 사용을 지양하도록 해야 한다");
    }

    @Test
    @DisplayName("stream map 변환하기")
    public void streamToMap() {
        // given
        Map<String, Long> freq;

        // when
        try (Stream<String> words = Stream.of("a", "b", "c", "d", "e")) {
            freq = words.collect(groupingBy(String::toUpperCase, counting()));
        }

        // then
        assertThat(freq).hasSize(5);
        assertThat(freq.keySet()).containsExactly("A", "B", "C", "D", "E");
    }

    @Test
    @DisplayName("collector list 변환")
    public void collectorToList() {
        // given
        final Map<String, Integer> scoreResult =
                Map.of("a+", 5, "a", 3, "b+", 4, "b", 2, "c+", 1, "c", 1);

        // when
        final List<String> top3 = scoreResult.keySet()
                .stream()
                .sorted(Comparator.comparing(scoreResult::get).reversed())
                .limit(3)
                .collect(Collectors.toList());

        // then
        assertThat(top3).contains("a+", "b+", "a");
    }

    @Test
    @DisplayName("collector map 변환")
    public void collectorToMap() {
        // when
        final Map<String, Drama> dramas = Stream.of(Drama.values())
                .collect(toMap(Object::toString, e -> e));

        // then
        assertThat(dramas.keySet()).hasSize(Drama.values().length);
    }

    @Test
    @DisplayName("각 키와 해당 키의 특정 원소를 연관 짓는 맵 생성 수집기 테스트")
    public void artistMap() {
        // given
        final Artist 박재범 = Artist.valueOf("박재범");
        final Artist 릴러말즈 = Artist.valueOf("릴러말즈");
        final Artist 긱스 = Artist.valueOf("긱스");

        final ArrayList<Album> albums = Lists.newArrayList(
                Album.valueOf("1집", 박재범, 5_000_000),
                Album.valueOf("2집", 박재범, 8_000_000),
                Album.valueOf("1집", 릴러말즈, 12_000_000),
                Album.valueOf("2집", 릴러말즈, 9_000_000),
                Album.valueOf("1집", 긱스, 1_000_000),
                Album.valueOf("2집", 긱스, 9_500_000)
        );

        // when
        final Map<Artist, Album> topHits = albums
                .stream().collect(toMap(Album::getArtist, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Album::getSales))));

        // then
        assertThat(topHits.values()).contains(
                Album.valueOf("2집", 박재범, 8_000_000),
                Album.valueOf("1집", 릴러말즈, 12_000_000),
                Album.valueOf("2집", 긱스, 9_500_000));
    }

    @Test
    @DisplayName("마지막 값을 취하는 수집기")
    public void storeLastInput() {
        //given
        final Artist 박재범 = Artist.valueOf("박재범");
        final Artist 릴러말즈 = Artist.valueOf("릴러말즈");
        final Artist 긱스 = Artist.valueOf("긱스");

        final ArrayList<Album> albums = Lists.newArrayList(
                Album.valueOf("1집", 박재범, 5_000_000),
                Album.valueOf("2집", 박재범, 8_000_000),
                Album.valueOf("1집", 릴러말즈, 12_000_000),
                Album.valueOf("2집", 릴러말즈, 9_000_000),
                Album.valueOf("1집", 긱스, 1_000_000),
                Album.valueOf("2집", 긱스, 9_500_000)
        );

        // when
        final Map<Artist, Album> recentlyAlbums = albums.stream()
                .collect(toMap(Album::getArtist, a -> a, (o1, o2) -> o2));

        assertThat(recentlyAlbums.values()).extracting("name").contains("2집");
    }

    @Test
    @DisplayName("하위 목록 분리 테스트: partitioningBy")
    public void partitioningBy() {
        // given
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        // when
        Map<Boolean, List<Integer>> groups =
                intList.stream()
                        .collect(Collectors.partitioningBy(s -> s > 6));

        final List<Integer> subListFalse = groups.get(false);
        final List<Integer> subListTrue = groups.get(true);

        // then
        assertThat(groups).hasSize(2);
        assertThat(subListFalse).hasSize(6);
        assertThat(subListTrue).hasSize(2);
    }

    @Test
    @DisplayName("문자열 접두/미문자 처리를 하는 joining 테스트")
    public void joining() {
        // given
        final String luckyNumbers = Stream.of(9, 8, 1, 33, 42, 25)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        // then
        assertThat(luckyNumbers).isEqualTo("1, 8, 9, 25, 33, 42");
    }
}
