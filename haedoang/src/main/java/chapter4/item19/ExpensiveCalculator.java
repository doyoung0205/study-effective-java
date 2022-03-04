package chapter4.item19;

/**
 * author : haedoang
 * date : 2022/03/04
 * description :
 */
public class ExpensiveCalculator extends Calculator {

    @Override
    public Object plus(String a, String b) {
        return Double.parseDouble(a) + Double.parseDouble(b);
    }
}
