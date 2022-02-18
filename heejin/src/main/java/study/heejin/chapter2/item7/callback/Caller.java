package study.heejin.chapter2.item7.callback;

public class Caller {
    private final Callee callee;

    public Caller() {
        Callback callback = new Callback() {
            @Override
            public void callbackMethod() {
                System.out.println("callback 메서드 호출");
            }
        };
        this.callee = new Callee(callback);
    }

    public Caller(Callback callbackMethod) {
        this.callee = new Callee(callbackMethod);
    }

    public void callbackMethod() {
        callee.startCallback();
    }

    public Callee getCallee() {
        return callee;
    }
}
