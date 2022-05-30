package study.heejin.chapter8.item56;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DocExamples<E> {
    // 메서드 주석 (333-334쪽)
    /**
     * Returns the element at the specified position in this list.
     *
     * <p>This method is <i>not</i> guaranteed to run in constant
     * time. In some implementations it may run in time proportional
     * to the element position.
     *
     * @param  index index of element to return; must be
     *         non-negative and less than the size of this list
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= this.size()})
     */
    E get(int index) {
        return null;
    }

    // 자기사용 패턴 등 내부 구현 방식을 명확히 드러내기 위해 @implSpec 사용 (335쪽)
    /**
     * Returns true if this collection is empty.
     *
     * @implSpec This implementation returns {@code this.size() == 0}.
     *
     * @return true if this collection is empty
     */
    public boolean isEmpty() {
        return false;
    }

    // 문서화 주석에 HTML이나 자바독 메타문자를 포함시키기 위해 @literal 태그 사용 (336쪽)
    /**
     * A geometric series converges if {@literal |r| < 1}.
     */
    public void fragment() {
    }

    // 문서화 주석 첫 '문장'에 마침표가 있을 때 요약 설명 처리 (337쪽)
    /**
     * A suspect, such as Colonel Mustard or {@literal Mrs. Peacock}.
     */
    public enum Suspect {
        MISS_SCARLETT, PROFESSOR_PLUM, MRS_PEACOCK, MR_GREEN, COLONEL_MUSTARD, MRS_WHITE
    }

    // 자바독 문서에 색인 추가하기 - 자바 9부터 지원 (338쪽)
    /**
     * This method complies with the {@index IEEE 754} standard.
     */
    public void fragment2() {
    }

    // 열거 상수 문서화 (339-340쪽)
    /**
     * An instrument section of a symphony orchestra.
     */
    public enum OrchestraSection {
        /** Woodwinds, such as flute, clarinet, and oboe. */
        WOODWIND,

        /** Brass instruments, such as french horn and trumpet. */
        BRASS,

        /** Percussion instruments, such as timpani and cymbals. */
        PERCUSSION,

        /** Stringed instruments, such as violin and cello. */
        STRING;
    }

    // 애너테이션 타입 문서화 (340쪽)
    /**
     * Indicates that the annotated method is a test method that
     * must throw the designated exception to pass.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTest {
        /**
         * The exception that the annotated test method must throw
         * in order to pass. (The test is permitted to throw any
         * subtype of the type described by this class object.)
         */
        Class<? extends Throwable> value();
    }
}
