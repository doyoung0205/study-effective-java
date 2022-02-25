package me.doyoung.studyeffectivejava.chapter2.item13;

import me.doyoung.studyeffectivejava.chapter2.item13.code.OnlyCloneMethod;
import me.doyoung.studyeffectivejava.chapter2.item13.code.OnlyCloneable;
import me.doyoung.studyeffectivejava.chapter2.item13.code.ParentChild;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CloneTest {

    @DisplayName("Cloneable 인터페이스만 구현해서는 clone 을 사용할 수 없다.")
    @Test
    void onlyCloneable() {
        final OnlyCloneable onlyCloneable = new OnlyCloneable();
//        onlyCloneable.clone(); // clone 메서드를 사용할 수 없다.
    }

    @DisplayName("clone 메서드만 오버라이딩 해서는 CloneNotSupportedException 예외가 발생한다.")
    @Test
    void onlyCloneMethod() {
        final OnlyCloneMethod onlyCloneMethod = new OnlyCloneMethod();
        assertThrows(CloneNotSupportedException.class, () -> {
            onlyCloneMethod.clone();
        });
    }

    @DisplayName("부모 클래스에서 부모클래스 타입의 객체를 생성해서 반환한다면" +
            "   자식 클래스의 clone 은 항상 부모 타입이 반환될 것 이다.")
    @Test
    void parentClone() {
        final ParentChild parentChild = new ParentChild();
        assertThrows(ClassCastException.class, () -> {
            final ParentChild clone = (ParentChild) parentChild.clone();
        });
    }
}
