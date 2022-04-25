package chapter6.item41;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Item41Test {
    @Test
    @DisplayName("마커 인터페이스 테스트")
    public void markerInterfaceTest() {
        // given
        final User user = User.valueOf("유저1");
        final Group group = Group.valueOf("그룹A");
        final AppService service = new AppService();

        // when
        service.delete(user);

        // then
        assertThatThrownBy(() -> service.delete(group))
                .isInstanceOf(IllegalStateException.class);
    }
}
