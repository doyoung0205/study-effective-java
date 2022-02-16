package study.heejin.chapter2.item3;

public class SingletonStaticFactory {

    private static final SingletonStaticFactory INSTANCE = new SingletonStaticFactory();

    private SingletonStaticFactory() {
    }

    public static SingletonStaticFactory getInstance() {
        return INSTANCE;
    }

    // 싱글턴임을 보장해주는 readResolve 메서드
    private Object readResolve() {
        // 진짜 싱글턴 인스턴스를 반환하고, 가짜 싱글턴 인스턴스는 가비지 컬렉터에 맡긴다.
        return INSTANCE;
    }

    public SingletonStaticFactory getSingleton() {
        System.out.println("정적 팩터리 방식의 싱글턴");
        return INSTANCE;
    }
}
