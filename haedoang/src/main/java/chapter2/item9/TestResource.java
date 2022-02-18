package chapter2.item9;

/**
 * packageName : chapter2.item9
 * fileName : TestResource
 * author : haedoang
 * date : 2022-02-18
 * description :
 */
public class TestResource implements AutoCloseable {

    public void run() {
        System.out.println("do run!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("저는 닫힐 때 호출이 됩니다.");
    }
}
