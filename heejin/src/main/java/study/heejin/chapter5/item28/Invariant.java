package study.heejin.chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Invariant {
    public static void main(String[] args) {
        Object[] objectArrray = new Long[1];
        // 런타임에 ArrayStoreException 을 던진다.
        objectArrray[0] = "타입이 달라 넣을 수 없다.";

        // 호환되지 않는 타입니다. 컴파일 시점에 에러가 표시된다.
        // List<Object> objectList = new ArrayList<Long>();
        // objectList.add("타입이 달라 넣을 수 없다.");
    }
}
