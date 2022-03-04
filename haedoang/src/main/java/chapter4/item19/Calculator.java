package chapter4.item19;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * author : haedoang
 * date : 2022/03/04
 * description :
 */
public class Calculator {

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 정수 이외의 값은 예외를 발생시키기 때문에 재정의 시 주.의.해.주.세.요!!!
     *
     * @throws IllegalArgumentException   {@inheritDoc}
     */
    public Object plus(String a, String b) {
        validate(a, b);
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 정수 이외의 값은 예외를 발생시키기 때문에 재정의 시 주.의.해.주.세.요!!!
     *
     * @throws IllegalArgumentException   {@inheritDoc}
     */
    public Object minus(String a, String b) {
        validate(a, b);
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    private void validate(String a, String b) {
        if (!NumberUtils.isDigits(a) || !NumberUtils.isDigits(b)) {
            throw new IllegalArgumentException();
        }
    }
}
