package study.heejin.chapter12.item87;

import java.io.Serializable;

/**
 * 기본 직렬화 형태에 적합한 클래스 - 인스턴스 필드가 논리적 구성요소를 반영
 */
public class Name implements Serializable {
    private static final long serialVersionUID = -678982891551268217L;

    /**
     * 성. null이 아니어야 함.
     * @serial
     */
    private final String lastName;
    /**
     * 이름. null이 아니어야 함.
     * @serial
     */
    private final String firstName;
    /**
     * 중간이름. 중간이름이 없다면 null.
     * @serial
     */
    private final String middleName;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
}
