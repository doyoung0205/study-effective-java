package chapter2.item5;

/**
 * packageName : chapter2.item5
 * fileName : LegacyMapUtil
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class LegacyMapUtil {
    private static final NaverMap map = new NaverMap();

    private LegacyMapUtil() {
    }

    public static Location getCurrentLocation() {
        return map.getCurrentLocation();
    }

    public static String mapClassName() {
        return map.getClass().getSimpleName();
    }
}
