package chapter11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/07/11
 * description :
 */
public class Item80Test {

    @Test
    @DisplayName("태스크가 완료되길 기다릴 수 있다")
    public void waitTask() throws ExecutionException, InterruptedException {
        // given
        final ExecutorService executor =
                Executors.newSingleThreadExecutor();

        // when
        final String actual = executor.submit(() -> {
            Thread.sleep(2000);
            System.out.println("sleep 2 second");

            return "Hello World";
        }).get();

        // then
        assertThat(actual).isEqualTo("Hello World");
    }
}
