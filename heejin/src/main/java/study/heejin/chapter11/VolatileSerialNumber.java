package study.heejin.chapter11;

/**
 * 일련번호를 생성할 의도로 작성한 아래의 메서드는 동기화하지 않더라도 불변식을 보호할 수 있어 보인다.
 *
 * 문제는 증가 연산자(++)다.
 * 이 연산자는 코드상으로는 하나지만 실제로는 nextSerialNumber 필드에 두 번 접근한다.
 * 만약 두 번째 스레드가 이 두 접근 사이를 비집고 들어와 값을 읽어가면 첫 번째 스레드와 똑같은 값을 돌려받게 된다.
 * 프로그램이 잘못된 결과를 계산해내는 이런 오류를 안전 실패라고 한다.
 */
public class VolatileSerialNumber {
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }
}
