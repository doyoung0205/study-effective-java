package chapter4.item16;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/01
 * description :
 */
class ClazzTest {

    @Test
    @DisplayName("inner class 테스트")
    public void innerClassTest() {
        // given
        //final Clazz.InnerClass innerClass = new Clazz.InnerClass(); //compile err

        // when
        final Clazz clazz = new Clazz();
        Clazz.NotUsefulInnerClass notUsefulInnerClass = clazz.new NotUsefulInnerClass();
        notUsefulInnerClass.run();

        // then
        assertThat(clazz).isNotNull();
        assertThat(notUsefulInnerClass).isNotNull()
                .as("outer class에 대한 참조가 없는 내부 클래스는 static class로 생성하는 편이 낫다")
                .as("불필요한 객체 생성을 발생한다");

    }

}