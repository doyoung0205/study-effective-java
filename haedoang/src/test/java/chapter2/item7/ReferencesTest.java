package chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item7
 * fileName : ReferencesTest
 * author : haedoang
 * date : 2022-02-14
 * description :
 */
@DisplayName("자바 참조 테스트")
public class ReferencesTest {
    List<String[]> strongReferences = new ArrayList();
    List<SoftReference<String[]>> softReferences = new ArrayList<>();
    List<WeakReference<String[]>> weakReferences = new ArrayList<>();

    @Test
    @DisplayName("강한 참조는 gc 대상이 아니다")
    public void strongReferences() {
        boolean isThrow = false;
        long callCount = 0;
        try {
            while (true) {
                callCount++;
                strongReferences.add(new String[1_000_000]);
            }
        } catch (OutOfMemoryError oome) {
            isThrow = true;
        }
        assertThat(isThrow).isTrue();
    }

    @Test
    @DisplayName("소프트 참조는 참조 객체가 SoftReference에만 존재하느 경우 gc 대상이 된다")
    public void softReferences() {
        boolean isThrow = false;
        long callCount = 0;
        try {
            while (true) {
                callCount++;
                softReferences.add(new SoftReference<>(new String[1_000_000]));
                if (callCount > 2_000) {
                    return;
                }
            }
        } catch (OutOfMemoryError oome) {
            isThrow = true;
        }
        assertThat(isThrow).isFalse();
    }

    @Test
    @DisplayName("약한 참조는 WeakReferences 객체만 존재하는 경우 GC 대상이 된다")
    public void weakReferences() {
        boolean isThrow = false;
        long callCount = 0;
        try {
            while (true) {
                callCount++;
                weakReferences.add(new WeakReference<>(new String[1_000_000]));
                if (callCount > 2_000) {
                    return;
                }
            }
        } catch (OutOfMemoryError oome) {
            isThrow = true;
        }
        assertThat(isThrow).isFalse();
    }
}
