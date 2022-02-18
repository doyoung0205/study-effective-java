package study.heejin.chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item7.reference.BigImage;
import study.heejin.chapter2.item7.reference.PhantomReferenceExample;
import study.heejin.chapter2.item7.reference.ReferenceExample;
import study.heejin.chapter2.item7.reference.UniqueImageName;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReferenceTest {

    /**
     * 강한 참조 (Strong Reference)
     * 강한 참조가 있는 객체는 GC대상이 되지않는다.
     *
     * 부드러운 참조 (Soft Reference)
     * 참조하는 객체가 해제되어 빈 SoftReference만 남으면 GC대상으로 들어간다.
     * 다만, 메모리가 부족하지 않으면 굳이 GC하지 않는다.
     *
     * 약한 참조 (Weak Reference)
     * 참조하는 객체가 해제되어 빈 WeakReference만 남으면 GC대상으로 들어간다.
     * 메모리가 부족하지 않더라도 GC 대상이 된다.
     *
     * 팬텀 참조 (Phantom Reference)
     * 올바르게 삭제하고 삭제 이후 작업을 조작하기 위해 사용한다.
     */

    @Test
    @DisplayName("SoftReference 테스트")
    void softReference() {
        Integer prime = 1;
        SoftReference<Integer> softReference = new SoftReference<>(prime);

        softReference.clear();
        assertThat(softReference.get()).isNull();
        assertThat(softReference).isNotNull();

        System.gc();
        assertThat(softReference).isNotNull();
    }

    @Test
    @DisplayName("WeakReference 테스트")
    void weakReference() {
        Integer prime = 1;
        WeakReference weakReference = new WeakReference<>(prime);

        weakReference.clear();
        assertThat(weakReference.get()).isNull();
        assertThat(weakReference).isNotNull();

        System.gc();
        assertThat(weakReference).isNull(); // 여기서 실패. 원래는 통과해야 하지만, System.gc()를 호출하더라도 GC가 동작한다고 보장할수는 없음
    }

    @Test
    @DisplayName("WeakHashMap 테스트")
    void weakHashMap() {
        WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<>();

        BigImage bigImageFirst = new BigImage("foo");
        UniqueImageName imageNameFirst = new UniqueImageName("name_of_big_image");

        BigImage bigImageSecond = new BigImage("foo_2");
        UniqueImageName imageNameSecond = new UniqueImageName("name_of_big_image_2");

        map.put(imageNameFirst, bigImageFirst);
        map.put(imageNameSecond, bigImageSecond);

        assertTrue(map.containsKey(imageNameFirst));
        assertTrue(map.containsKey(imageNameSecond));

        imageNameFirst = null;
        System.gc();

        await().atMost(10, TimeUnit.SECONDS)
                .until(() -> map.size() == 1);
        await().atMost(10, TimeUnit.SECONDS)
                .until(() -> map.containsKey(imageNameSecond));
    }
    
    @Test
    @DisplayName("PhantomReference 테스트")
    void phantomReference() {

        PhantomReferenceExample.start();

    }

    @Test
    @DisplayName("GC 실행 테스트")
    void outOfMemory() {
        System.out.println("===== 실행 =====");

        ReferenceExample re = new ReferenceExample();
//        re.strongReferenceTest();
//        re.softReferenceTest();
//        re.weakReferenceTest();

        System.out.println("===== 종료 =====");
    }
}
