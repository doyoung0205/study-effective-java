package chapter10.item75;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/06/25
 * description :
 */
public class Item75Test {
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(Lists.newArrayList(
                new User(1L, "user1", 30),
                new User(2L, "user2", 30),
                new User(3L, "user3", 30)
        ));
    }

    @Test
    @DisplayName("사용자가 존재하지 않는 경우 일반 예외를 반환 테스트")
    public void userNotExistThrowNormalException() {
        // given
        Long NOT_EXIST_ID = Long.MAX_VALUE;

        // then
        assertThatThrownBy(() -> {
            final User user = this.userService.findById(NOT_EXIST_ID)
                    .orElseThrow(NoSuchElementException::new);
        }).isInstanceOf(NoSuchElementException.class)
                .as("직관적이지 못함");
    }

    @Test
    @DisplayName("사용자가 존재하지 않는 경우 커스텀 예외를 반환 테스트")
    public void userNotExistThrowCustomException() {
        // given
        Long NOT_EXIST_ID = Long.MAX_VALUE;

        // then
        assertThatThrownBy(() -> {
            final User user = this.userService.findById(NOT_EXIST_ID)
                    .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_ID));
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(UserNotFoundException.MESSAGE);
    }
}
