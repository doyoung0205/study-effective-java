package chapter11;

import chapter11.item79.AdvancedObservableSet;
import chapter11.item79.AdvancedSetObserver;
import chapter11.item79.BestObservableSet;
import chapter11.item79.BestSetObserver;
import chapter11.item79.ObservableSet;
import chapter11.item79.SetObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class item79Test {

    @Test
    @DisplayName("set Observer 테스트")
    public void addObserver() {
        // given
        final ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());
        set.addObserver((s, e) -> System.out.println(e));

        // when
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        // then
        assertThat(set).hasSize(100);
    }

    @Test
    @DisplayName("set Observer 테스트")
    public void removeObserverTest() {
        // given
        final ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });

        // then
        assertThatThrownBy(() -> {
            for (int i = 0; i < 100; i++) {
                set.add(i);
            }
        }).isInstanceOf(ConcurrentModificationException.class)
                .as("컬렉션 순회 도중 원소를 제거하여 예외가 발생한다");
    }

    @Test
    @DisplayName("데드락이 발생하는 백그라운드 스레드 사용")
    public void usingBackgroundFollowDeadLock() {
        // given
        final ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> s, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec =
                            Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        // when
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        // then
        throw new AssertionError("교착상태에 빠진다");
    }

    @Test
    @DisplayName("외계인 메서드를 동기화 블록 바깥으로 옮긴다")
    public void reentrantTest() {
        // given
        final AdvancedObservableSet<Integer> set =
                new AdvancedObservableSet<>(new HashSet<>());

        set.addObserver(new AdvancedSetObserver<>() {
            @Override
            public void added(AdvancedObservableSet<Integer> s, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec =
                            Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        // when
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        // then
        assertThat(set).hasSize(100)
                .as("23 이후 observer가 제거되어 로깅되지 않음");
    }

    @Test
    @DisplayName("CopyOnWriteArrayList 사용한 관찰자 테스트")
    public void observerWithCopyOnWriteArrayList() {
        // given
        final BestObservableSet<Integer> set =
                new BestObservableSet<>(new HashSet<>());

        set.addObserver(new BestSetObserver<>() {
            @Override
            public void added(BestObservableSet<Integer> s, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec =
                            Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        // when
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        // then
        assertThat(set).hasSize(100)
                .as("23 이후 observer가 제거되어 로깅되지 않음");
    }
}

