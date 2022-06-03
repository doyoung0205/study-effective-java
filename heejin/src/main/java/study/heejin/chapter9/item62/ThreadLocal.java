package study.heejin.chapter9.item62;

public final class ThreadLocal<T> {
    private final java.lang.ThreadLocal theadLocal = new java.lang.ThreadLocal<>();

    public ThreadLocal() {
    }

    public void set(T value) {
        theadLocal.set(value);
    }

    public T get() {
        return (T) theadLocal.get();
    }
}
