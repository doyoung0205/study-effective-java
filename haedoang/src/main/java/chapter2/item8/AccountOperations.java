package chapter2.item8;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
public class AccountOperations {

    public AccountOperations() {
        if (!isAuthorized()) {
            throw new SecurityException("권한이 없습니다");
        }
    }

    public boolean isAuthorized() {
        return false;
    }

    public void transferMoney(double amount) {
        System.out.println("송금 중: " + amount + "원");
    }
}
