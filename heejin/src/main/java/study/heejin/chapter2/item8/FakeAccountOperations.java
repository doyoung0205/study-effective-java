package study.heejin.chapter2.item8;

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
