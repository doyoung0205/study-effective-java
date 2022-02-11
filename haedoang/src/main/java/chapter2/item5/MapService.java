package chapter2.item5;

import java.util.Objects;

/**
 * packageName : chapter2.item5
 * fileName : MapUtil
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class MapService {
    private final Map map;

    private MapService(Map map) {
        this.map = Objects.requireNonNull(map);
    }

    public static MapService valueOf(Map map) {
        return new MapService(map);
    }

    public Location getCurrentLocation() {
        return map.getCurrentLocation();
    }

    public String mapClassName() {
        return map.getClass().getSimpleName();
    }
}
