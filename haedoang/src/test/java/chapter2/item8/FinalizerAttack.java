package chapter2.item8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
public class FinalizerAttack {

    @Test
    @DisplayName("생성 불가 객체 테스트")
    public void preventCreateObj() {
        Assertions.assertThatThrownBy(() -> new AccountOperations())
                .isInstanceOf(SecurityException.class);
    }

    @Test
    @DisplayName("finalizer를 통해 허가되지 않은 메서드 호출하기")
    public void finalizerAttack() {
        // when
        try {
            new FakeAccountOperations();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        // then
        System.gc();
    }
}
