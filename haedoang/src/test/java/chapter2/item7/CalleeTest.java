package chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : chapter2.item7
 * fileName : CalleeTest
 * author : haedoang
 * date : 2022-02-14
 * description :
 */
class CalleeTest {

    @Test
    @DisplayName("익명구현객체로 콜백 구현")
    public void callback() {
        // given
        Callee callee = new Callee(new Callback() {
            @Override
            public String run(String command) {
                System.out.println("callback method invoke");
                System.out.println("command: " + command);
                return command;
            }
        });
        // when
        String command = callee.execute();

        // then
        assertThat(command).isEqualTo("do run!!")
                .as("Callee는 callback 익명구현객체를 통해 execute()메서드가 호출하였을 떄")
                .as("callback 함수를 호출한다. 즉 Callback 익명구현객체는 Callee에 의해 발생한 이벤트에 대한 응답이 가능하다");
    }

}
