package chapter8.item52;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * author : haedoang
 * date : 2022/05/21
 * description :
 */
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> list) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return "Etc";
    }

    public static String classifyAdv(Collection<?> c) {
        return c instanceof Set ? "Set"
                : c instanceof List ? "List" : "Etc";
    }
}
