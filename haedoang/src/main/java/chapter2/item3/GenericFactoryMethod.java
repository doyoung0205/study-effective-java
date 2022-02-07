package chapter2.item3;

import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/02/07
 * description :
 */
public class GenericFactoryMethod {
    private static final List EMPTY_LIST = new ArrayList();

    public static final <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
}
