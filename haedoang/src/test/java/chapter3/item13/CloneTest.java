package chapter3.item13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter3.iterm13
 * fileName : KoreanPersonTest
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
class CloneTest {

    @Test
    @DisplayName("clone한 객체는 원래 객체와 다른 객체이어야 한다")
    public void notEqualToOriginal() throws CloneNotSupportedException {
        // given
        KoreanMan koreanPerson = new KoreanMan("haedoang", 34, "떡볶이");

        KoreanMan clonedKoreanPerson = (KoreanMan) koreanPerson.clone(); //공변 반환 타입 적용

        // then
        assertThat(koreanPerson.getName()).isEqualTo(clonedKoreanPerson.getName()).isEqualTo("haedoang");
        assertThat(koreanPerson.getAge()).isEqualTo(clonedKoreanPerson.getAge()).isEqualTo(34);
        assertThat(koreanPerson.getFavoriteFood()).isEqualTo(clonedKoreanPerson.getFavoriteFood()).isEqualTo("떡볶이");

        assertThat(koreanPerson.getClass()).isEqualTo(clonedKoreanPerson.getClass());
        assertThat(koreanPerson).isNotSameAs(clonedKoreanPerson);
    }


    @Test
    @DisplayName("상속 관계에서 clone 주의할 점 테스트")
    public void cloneFailWhenInheritance() throws CloneNotSupportedException {
        // given
        JapaneseWomen japanesePerson = new JapaneseWomen("나까무라", 22, "눈의꽃");


        // then
        assertThatThrownBy(() -> {
            JapaneseWomen clonedJapanesePerson = (JapaneseWomen) japanesePerson.clone();
        }).isInstanceOf(ClassCastException.class)
                .as("clone은 new()와 기능적으로 같다. 상속관계에서 clone 시 주의해야할 점은 ")
                .as("상위 클래스가 clone 시 Object의 clone을 재정의하지 않고 new() 생성자로 객체를 반환하는 경우")
                .as("하위 객체에서 super.clone()할 경우 상위 객체 타입을 반환하기 떄문에 제대로 동작하지 않게 된다.");
    }

    @Test
    @DisplayName("가변 상태를 참조하지 않는 클래스의 cloneTest")
    public void nonVariableStateClone() throws CloneNotSupportedException {
        // given
        PhoneNumber phoneNumber = new PhoneNumber(02, 123, 3456);

        // when
        PhoneNumber actual = phoneNumber.clone();

        // then
        assertThat(phoneNumber).isNotSameAs(actual);
        assertThat(phoneNumber).isEqualTo(actual);
    }

    @Test
    @DisplayName("가변상태의 경우 같은 오브젝트를 참조하게 되어 문제가 될 수 있다.")
    public void variableStateClone() throws CloneNotSupportedException {
        // given
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.nonNullElementsSize()).isEqualTo(3);

        // when
        Stack actual = (Stack) stack.clone();

        assertThat(actual.nonNullElementsSize()).isEqualTo(3);
        assertThat(stack).isNotSameAs(actual);
        assertThat(stack.getElementsHashCode()).isEqualTo(actual.getElementsHashCode());
    }


    @Test
    @DisplayName("가변상태의 경우 가변상태의 객체도 clone해주어야 한다")
    public void variableStateCloneUpdate() throws CloneNotSupportedException {
        // given
        NewStack stack = new NewStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.nonNullElementsSize()).isEqualTo(3);

        // when
        NewStack actual = stack.clone();

        assertThat(actual.nonNullElementsSize()).isEqualTo(3);
        assertThat(stack).isNotSameAs(actual);
        assertThat(stack.getElementsHashCode()).isNotEqualTo(actual.getElementsHashCode());
    }

    @Test
    @DisplayName("복사 생성자 & 복사 팩터리를 구현한 Queue 테스트")
    public void queueTest() throws IllegalAccessException {
        // given
        final Queue queue = new Queue(5);

        // when
        queue.push("안녕하세요");
        queue.push("우리는");
        queue.push("BTS입니다.");

        //복사 생성자
        final Queue clonedQueue = new Queue(queue);
        final Queue clonedQueue2 = Queue.newInstance(queue);

        // then
        assertThat(queue.pop()).isEqualTo(clonedQueue.pop())
                .isEqualTo(clonedQueue2.pop())
                .isEqualTo("안녕하세요");
        assertThat(queue.pop()).isEqualTo(clonedQueue.pop())
                .isEqualTo(clonedQueue2.pop())
                .isEqualTo("우리는");
        assertThat(queue.pop()).isEqualTo(clonedQueue.pop())
                .isEqualTo(clonedQueue2.pop())
                .isEqualTo("BTS입니다.");
        assertThat(queue).isNotSameAs(clonedQueue)
                .isNotSameAs(clonedQueue2);
    }
}
