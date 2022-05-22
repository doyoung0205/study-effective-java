package chapter8.item52;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/21
 * description :
 */
public class Item52Test {
    @Test
    @DisplayName("다중정의 메서드 테스트")
    public void collectionClassifierNG() {
        // given
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        // when
        final List<String> actual = Arrays.stream(collections)
                .map(CollectionClassifier::classify)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsOnly("Etc")
                .as("컴파일타임에 Collection 타입으로 정해지기 때문에 Etc로 반환한다");
    }

    @Test
    @DisplayName("다중정의 혼동 피하는 메서드 테스트")
    public void collectionClassifierOK() {
        // given
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        // when
        final List<String> actual = Arrays.stream(collections).map(CollectionClassifier::classifyAdv)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsExactly("Set", "List", "Etc");
    }


    @Test
    @DisplayName("재정의 메서드 호출 테스트")
    public void overrideTest() {
        // given
        List<Wine> wines = List.of(new Wine(), new SparklingWine(), new Champagne());

        // when
        final List<String> actual = wines.stream().map(Wine::name)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsExactly("포도주", "발포성 포도주", "샴페인")
                .as("재정의한 메서드는 동적으로 선택된다.");
    }

    public static void main(String[] args) {
        //다중정의 메서드
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit((Runnable) System.out::println); // Runnable, Callable 이 다중정의되어있음
    }
}
