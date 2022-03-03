package chapter4.item17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chapter4.item17.AutomaticTransfers.*;
import static chapter4.item17.AutomaticTransfers.전기세;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
class MoneyTest {

    @Test
    @DisplayName("불변 객체 만들기 테스트")
    public void immutableObject() {
        // given
        final int price = 1_000;
        final Money money = Money.valueOf(price);

        // then
        assertThat(money).isEqualTo(Money.valueOf(1_000));
    }

    @Test
    @DisplayName("함수형 프로그래밍은 인스턴스를 수정하지 않고 새 인스턴스를 반환한다")
    public void functionalProgrammingTest() {
        // given
        final int price = 1_000;
        final Money money = Money.valueOf(price);

        // when
        final Money actual = money.plus(Money.valueOf(10_000));

        // then
        assertThat(actual).isEqualTo(Money.valueOf(11_000));
        assertThat(actual).isNotSameAs(money);
    }

    public static void main(String[] args) {
        //멀티쓰레드 상황에서 불변객체가 아닌 경우 문제 되는 예제 샘플 코드 작성하기

        // TEST 1 멀티 스레드 상황 만들기
        final CustomThread customThreadA = new CustomThread();
        final CustomThread customThreadB = new CustomThread();

        final Thread threadA = new Thread(customThreadA, "customThreadA");
        final Thread threadB = new Thread(customThreadB, "customThreadB");

        threadA.start();
        threadB.start();

        // TEST2 가변 객체 문제점 상황
        // 사용자 계좌를 가지고 있는 객체에서 조회한 금액이 없는 경우 예외 발생 시키기

        final Account account = new Account();
        final Thread 보험인출 = new Thread(account, 보험.name());
        final Thread 통신비인출 = new Thread(account, 통신비.name());
        final Thread 은행이자인출 = new Thread(account, 은행이자.name());
        final Thread 관리비인출 = new Thread(account, 관리비.name());
        final Thread 가스비인출 = new Thread(account, 가스비.name());
        final Thread 전기세인출 = new Thread(account, 전기세.name());
        final Thread 뽐뿌인출 = new Thread(account, "아이패드지를거임");
        보험인출.start();
        통신비인출.start();
        은행이자인출.start();
        관리비인출.start();
        가스비인출.start();
        뽐뿌인출.start();
        전기세인출.start();


        // TEST3 멀티스레드 상황에서 동기화 처리

        final SynchronizedAccount synchronizedAccount = new SynchronizedAccount();
        final Thread _보험인출 = new Thread(synchronizedAccount, 보험.name());
        final Thread _통신비인출 = new Thread(synchronizedAccount, 통신비.name());
        final Thread _은행이자인출 = new Thread(synchronizedAccount, 은행이자.name());
        final Thread _관리비인출 = new Thread(synchronizedAccount, 관리비.name());
        final Thread _가스비인출 = new Thread(synchronizedAccount, 가스비.name());
        final Thread _전기세인출 = new Thread(synchronizedAccount, 전기세.name());
        final Thread _뽐뿌인출 = new Thread(synchronizedAccount, "아이패드지를거임");
        _보험인출.start();
        _통신비인출.start();
        _은행이자인출.start();
        _관리비인출.start();
        _가스비인출.start();
        _뽐뿌인출.start();
        _전기세인출.start();
    }
}