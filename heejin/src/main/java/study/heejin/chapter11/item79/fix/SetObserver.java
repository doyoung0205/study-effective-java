package study.heejin.chapter11.item79.fix;

/**
 * 집합 관찰자 콜백 인터페이스
 *
 * 이 인터페이스는 구조적으로 BiConsumer<ObservableSet<E>, E>와 똑같다.
 * 그럼에도 커스텀 함수형 인터페이스를 정의한 이유는 이름이 더 직관적이고, 다중 콜백을 지원하도록 확장할 수 있어서이다.
 * (하지만 BiConsumer를 그대로 사용해도 무리는 없을 것이다.)
 */
public interface SetObserver<E> {

    /**
     * ObservableSet에 원소가 더해지면 호출된다.
     * @param set
     * @param element
     */
    void added(ObservableSet<E> set, E element);

}
