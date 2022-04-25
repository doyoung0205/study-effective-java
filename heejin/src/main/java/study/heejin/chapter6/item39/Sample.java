package study.heejin.chapter6.item39;

public class Sample {

    @Test
    public static void m1() { // 성공
    }

    public static void m2() { // 패스
    }

    @Test
    public static void m3() { // 실패
        throw new RuntimeException("실패");
    }

    public static void m4() { // 패스
    }

    @Test
    public void m5() { // 잘못 사용한 예: 정적 메서드가 아니다.
    }

    public static void m6() { // 패스
    }

    public static void m7() { // 실패
        throw new RuntimeException("실패");
    }

    public static void m8() { // 패스
    }
}
