package study.heejin.chapter5.item31;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class RecursiveTypeBound {

    /**
     * item 30 에서 다라진 점
     * <p>
     * - 입력 매개변수 E 는 Collection<E> 에서 List<? extends E> 로 수정하였다.
     * - 타입 매개변수 E 는 <E extends Comparable<E>> 에서 <E extends Comparable<? super E>로 수정하였다.
     * - Comparable<E> 는 E 인스턴스를 소비하므로, Comparable<? super E>로 수정하였다.
     * - Comparator도 마찬가지로 Comparator<E> 보다는 Comparator<? super E>를 사용하는 편이 낫다.
     */
    public static <E extends Comparable<? super E>> E max(List<? extends E> list) throws ExecutionException, InterruptedException {
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E result = null;
        for (E e : list) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        List<ScheduledFuture<?>> schedules = Arrays.asList(
                executor.schedule(() -> 3, 1, TimeUnit.SECONDS),
                executor.schedule(() -> 5, 1, TimeUnit.SECONDS),
                executor.schedule(() -> 11, 3, TimeUnit.SECONDS),
                executor.schedule(() -> 7, 2, TimeUnit.SECONDS),
                executor.schedule(() -> 2, 1, TimeUnit.SECONDS)
        );

        ScheduledFuture<?> max = max(schedules);
        System.out.println("@@@ max = " + max.get()); // delay의 max 값

        System.exit(0);
    }
}
