package chapter5.item28;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : chapter4.item28
 * fileName : Machine
 * author : haedoang
 * date : 2022-03-24
 * description :
 */
public class Machine<T> {
    private List<T> versions = new ArrayList<>();

    @SafeVarargs
    public final void safe(T... toAdd) {
        for (T version : toAdd) {
            versions.add(version);
        }
    }

    public int hasSize() {
        return versions.size();
    }
}
