package study.heejin.chapter11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter11.item79.ObservableSet;
import study.heejin.chapter11.item79.SetObserver;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Item79Test {

    @Test
    @DisplayName("관찰자 패턴을 적용하여 값이 추가될 때 0 부터 99까지 출력한다.")
    void addObserver() {
        // given
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // when
        set.addObserver((s, e) -> System.out.println(e));

        // then
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    @Test
    @DisplayName("관찰자 패턴을 적용하여 값이 추가될 때, 23에 도달하면 구독을 해지한다. -> 실제로는 ConcurrentModificationException을 던진다.")
    void addObserverIfStop23() {
        // given
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // when
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);

                if (element == 23) {
                    set.removeObserver(this); // 람다는 자신을 참조할 수 없기 때문에 익명 클래스 사용
                }
            }
        });

        // then
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    @Test
    @DisplayName("관찰자 패턴을 적용하여 값이 추가될 때, 실행자 서비스를 사용해 다른 스레드에서 구독을 해지한다. -> 실제로는 교착상태에 빠진다.")
    void addObserverWithExecutorService() {
        // given
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // when
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);

                if (element == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();

                    try {
                        exec.submit(() -> set.removeObserver(this)).get();

                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);

                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        // then
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
