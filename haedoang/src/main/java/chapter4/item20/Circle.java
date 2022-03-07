package chapter4.item20;

import java.util.Arrays;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public interface Circle {
    List<String> allowsColors = Arrays.asList("BLUE", "GREEN", "RED");

    String getColor();

    default boolean isValid() {
        if (!this.allowsColors.contains(getColor())) {
            return false;
        }
        return true;
    }
}
