package chapter10.item73;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class Item73Test {

    @Test
    @DisplayName("예외 번역은 저수준 예외를 고수준 예외로 추상화 수준에 맞는 예외로 바꾸는 것을 말한다")
    public void exceptionTranslation() {
        Assertions.assertThatThrownBy(() -> {
                    try {
                        throw new LowerException("저수준 예외입니다!");
                    } catch (LowerException e) {
                        throw new HigherException("고수준 예외입니다!");
                    }
                }).isInstanceOf(HigherException.class)
                .as("저수준 예외를 고수준 예외로 추상화하는 것을 예외 번역이라고 부른다.");
    }

    @Test
    @DisplayName("예외 연쇄용 생성자")
    public void exceptionChaining() {
        Assertions.assertThatThrownBy(() ->
                {
                    try {
                        throw new LowerException("저수준 예외입니다.");
                    } catch (LowerException e) {
                        throw new HigherException(e.getCause());
                    }
                }).isInstanceOf(HigherException.class)
                .as("저수준 예외를 고수준 예외로 추상화하는 것을 예외 번역이라고 부른다.");

    }

}