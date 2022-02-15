package chapter2.item8;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
public class FakeAccountOperations extends AccountOperations {

    public FakeAccountOperations() {
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("접근 불가 메서드 호출하기");
        this.transferMoney(5000);
        System.exit(0);
    }
}
