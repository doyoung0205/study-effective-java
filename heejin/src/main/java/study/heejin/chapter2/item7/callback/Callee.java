package study.heejin.chapter2.item7.callback;

public class Callee {
    private final boolean condition;
    private final Callback callback;

    public Callee() {
        this.condition = false;
        this.callback = null;
    }

    public Callee(Callback callback) {
        this.condition = true;
        this.callback = callback;
    }

    public void startCallback() {
        callback.callbackMethod();
    }

    // 콜백 메서드를 호출할 수 있는지 확인
    private void checkCondition() {
        if(condition && (callback != null)) {
            callback.callbackMethod(); // 가능하면 콜백 메서드 호출
        }
    }
}
