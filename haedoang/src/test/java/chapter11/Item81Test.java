package chapter11;

import chapter11.item81.Data;
import chapter11.item81.MapUtils;
import chapter11.item81.Receiver;
import chapter11.item81.Sender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/07/11
 * description :
 */
public class Item81Test {

    @Test
    @DisplayName("스레드 세이프하지 않은 hashMap 테스트")
    public void notThreadSafeHashMap() throws InterruptedException {
        // given
        final HashMap<String, Integer> hashMap = new HashMap<>();

        // when
        final List<Integer> actual = MapUtils.parallelSum100(hashMap, 100);

        // then
        assertThat(actual.stream().filter(it -> it != 100).count()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스레드 세이프한 ConcurrentHashMap 테스트")
    public void threadSafeConcurrentHashMap() throws InterruptedException {
        // given
        final ConcurrentHashMap<String, Integer> concurrentHashMap =
                new ConcurrentHashMap<>();

        // when
        final List<Integer> actual = MapUtils.parallelSum100(concurrentHashMap, 100);

        // then
        assertThat(actual.stream().filter(it -> it != 100).count()).isEqualTo(0);
    }

    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
