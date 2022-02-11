package chapter2.item5;

/**
 * packageName : chapter2.item5
 * fileName : NaverMap
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class NaverMap implements Map {
    public Location getCurrentLocation() {
        return Location.valueOf(37.50228393383162, 127.09589382864698);
    }
}
