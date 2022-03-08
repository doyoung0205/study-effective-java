package chapter4.item15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter4
 * fileName : ArrayTest
 * author : haedoang
 * date : 2022-02-28
 * description :
 */
public class ClassAccessTest {

    @Test
    @DisplayName("public static array는 외부에서 값을 변경할 수 있다")
    public void publicArrayUpdateValue() {
        // given
        assertThat(SingerFactory.singers).containsExactly("Queen", "Ariana Grande", "Weekend");

        // when
        String oldSinger = SingerFactory.singers[1];
        SingerFactory.singers[1] = "Kelly Clarkson";

        // then
        assertThat(SingerFactory.singers[1]).isNotEqualTo(oldSinger);
        assertThat(SingerFactory.singers[1]).isEqualTo("Kelly Clarkson");
    }


    @Test
    @DisplayName("Collections.unmodifiableList는 수정 불가능한 프록시 객체를 반환하여 UnsupportedOperationException 반환한다")
    public void unmodifiableListTest() {
        List<String> singers = SingerFactory.SINGERS;

        // then
        assertThatThrownBy(() -> singers.set(0, "변경시도!!"))
                .isInstanceOf(UnsupportedOperationException.class);
    }


    @Test
    @DisplayName("외부에 공개할 API를 clone()을 활용하여 참조 객체를 숨기는 방법 테스트 ")
    public void arrayCloneTest() {
        // given
        assertThat(SingerFactory.getValue()).containsExactly("Queen", "Ariana Grande", "Weekend");
        String[] singers = SingerFactory.getValue();

        // when
        singers[1] = "Kelly Clarkson";

        // then
        assertThat(SingerFactory.getValue()).containsExactly("Queen", "Ariana Grande", "Weekend");
    }
}


class SingerFactory {
    public static final String[] singers = new String[]{"Queen", "Ariana Grande", "Weekend"};

    private static final String[] privateSingers = singers.clone();

    public static final List<String> SINGERS = Collections.unmodifiableList(Arrays.asList(privateSingers));

    public static String[] getValue() {
        return privateSingers.clone();
    }
}
