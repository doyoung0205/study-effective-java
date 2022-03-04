package chapter4.item17;

import static chapter4.item17.AutomaticTransfers.valueOf;

/**
 * author : haedoang
 * date : 2022/03/04
 * description :
 */
public class SynchronizedAccount implements Runnable {
    private Money money = Money.valueOf(1_000_000);

    @Override
    public synchronized void run() {
        try {
            final AutomaticTransfers automaticTransfers = valueOf(Thread.currentThread().getName());
            System.out.printf("%s 인출 요청 : -%s \n", Thread.currentThread().getName(), automaticTransfers.getPrice());
            withdraw(automaticTransfers.getPrice());
        } catch (IllegalArgumentException e) {
            System.out.printf("%s 인출 요청 : -%s \n", Thread.currentThread().getName(), 1_500_000);
            withdraw(Money.valueOf(1_500_000));
        }
    }

    private void withdraw(Money money) {
        Money updatedMoney = this.money.minus(money);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (updatedMoney.isPositive()) {
            this.money = updatedMoney;
            System.out.printf("%s 잔액: %s\n", Thread.currentThread().getName(), this.money);
        } else {
            System.out.printf("%s 출금할 수 없습니다. 잔액: %s\n", Thread.currentThread().getName(), this.money);
        }
    }
}
