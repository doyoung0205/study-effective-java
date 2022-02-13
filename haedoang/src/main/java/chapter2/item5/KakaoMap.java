package chapter2.item5;

/**
 * packageName : chapter2.item5
 * fileName : KakaoMap
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class KakaoMap implements Map {
    @Override
    public Location getCurrentLocation() {
        return Location.valueOf(37.50228393383162, 127.09589382864698);
    }
}
