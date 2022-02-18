package chapter2.item10;

import java.awt.*;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
public class ColorPointNew {
    private final Color color;
    private final Point point;

    public ColorPointNew(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPointNew)) {
            return false;
        }
        ColorPointNew cp = (ColorPointNew) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
