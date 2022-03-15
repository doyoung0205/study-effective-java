package me.doyoung.studyeffectivejava.chapter1.item8;

import me.doyoung.studyeffectivejava.chapter1.item8.code.Account;
import me.doyoung.studyeffectivejava.chapter1.item8.code.BrokenAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FinalizerAttackTest {

    @Test
    void 일반_사람() {
        Account account = new Account("whiteship");
        account.transfer(100, "dory");
    }

    @Test
    void 푸틴() {
        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new Account("푸틴");
            account.transfer(100, "dory");
        });
    }

    @Test
    void 푸틴_해킹() throws InterruptedException {
        Account account = null;
        try {
            account = new BrokenAccount("푸틴");
        } catch (Exception exception) {
            System.out.println("푸틴은 안되는데?");
        }

        System.gc();
        Thread.sleep(10000L);
    }
}
