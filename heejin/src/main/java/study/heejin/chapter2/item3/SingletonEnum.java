package study.heejin.chapter2.item3;

public enum SingletonEnum {

    INSTANCE;

    public String getSingleton() {
        return "열거 타입 방식의 싱글턴";
    }
}
