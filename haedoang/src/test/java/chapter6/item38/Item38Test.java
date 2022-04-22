package chapter6.item38;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * author : haedoang
 * date : 2022/04/19
 * description :
 */
public class Item38Test {
    @Test
    @DisplayName("확장가능한 계산기 테스트")
    public void extendedOperationTest() {
        // then
        test(Arrays.asList(BasicOperation.values()), 2, 4);
    }

    static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f\n", x, op, y, op.apply(x, y));
        }
    }
}

