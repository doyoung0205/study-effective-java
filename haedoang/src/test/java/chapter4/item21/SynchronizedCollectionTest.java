package chapter4.item21;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter4.item21
 * fileName : SynchronizedCollectionTest
 * author : haedoang
 * date : 2022-03-08
 * description :
 */
public class SynchronizedCollectionTest {


    @Test
    @DisplayName("멀티쓰레드에 안전한 synchronizedCollectionTest")
    @RepeatedTest(100)
    public void synchronizedCollectionTest() throws InterruptedException {
        // given
        Collection<String> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        Runnable addAll = () -> syncCollection.addAll(Arrays.asList("씻기", "옷입기", "출근하기", "퇴근하기"));

        Thread thread1 = new Thread(addAll);
        Thread thread2 = new Thread(addAll);

        // when
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();   //스레드가 죽을때까지 대기함

        // then
        assertThat(syncCollection.size()).isEqualTo(8);
    }


    @Disabled(value = "멀티스레드 상황에서 에외 발생 테스트. 간헐적 실패")
    @Test
    @DisplayName("멀티스레드 처리가 되어있지 않은 arrayListTest")
    @RepeatedTest(100)
    public void nonSynchronizedTest() throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();
        Runnable addAll = () -> list.addAll(Arrays.asList("씻기", "옷입기", "출근하기", "퇴근하기"));

        Thread thread1 = new Thread(addAll);
        Thread thread2 = new Thread(addAll);

        // when
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();   //스레드가 죽을때까지 대기함

        // then
        assertThat(list).hasSize(8);
    }

}
