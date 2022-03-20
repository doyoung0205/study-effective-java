package chapter5.item26;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * author : haedoang
 * date : 2022/03/20
 * description :
 */
public class CollectionUtil {
    private CollectionUtil() {
    }

    public static int numElementsInCommon(Set set1, Set set2) {
        int result = 0;
        for (Object o1 : set1) {
            if (set2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    public static int numElementsInCommonWildcardType(Set<?> set1, Set<?> set2) {
        int result = 0;
        for (Object o1 : set1) {
            if (set2.contains(o1)) {
                result++;
            }
        }
        return result;
    }


    public static Set<Object> mergeObjectSet(Set<Object> set1, Set<Object> set2) {
        return Sets.newHashSet(set1, set2);
    }
}
