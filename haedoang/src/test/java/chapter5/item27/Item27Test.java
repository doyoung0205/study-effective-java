package chapter5.item27;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * author : haedoang
 * date : 2022/03/19
 * description :
 */
public class Item27Test {

    @Test
    @DisplayName("비검사 경고를 사용하는 코드 테스트")
    public void nonInspectionWarning() {
        // given
        Set<String> btsMembers = new HashSet();

        // when
        btsMembers.add("정국");
        btsMembers.add("지민");
        btsMembers.add("RM");
        btsMembers.add("뷔");
        btsMembers.add("슈가");
        btsMembers.add("진");
        btsMembers.add("제이홉");

        // then
        assertThat(btsMembers).hasSize(7)
                .as("타입 매개변수를 명시하거나 <> 다이아몬드 연산자를 사용한다");
    }


    @Test
    @DisplayName("같은 타입을 반환하는 배열 복사 테스트")
    public void useSuppressWarningsTest() {
        // given
        String[] strArr = {"a","b","c"};

        // when
        final String[] actual = ArrayUtil.arrayCopy(strArr);

        // then
        assertThat(strArr).isEqualTo(actual)
                .as("생성한 배열과 매개변수로 받는 배열의 타입이 같기 때문에 형변환 문제가 되지 않는다");
    }
}
