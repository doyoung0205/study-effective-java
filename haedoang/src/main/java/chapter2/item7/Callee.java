package chapter2.item7;

/**
 * packageName : chapter2.item7
 * fileName : Callee
 * author : haedoang
 * date : 2022-02-14
 * description :
 */
public class Callee {
    private Callback callback;

    public Callee(Callback callback) {
        this.callback = callback;
    }

    public String execute() {
        return callback.run("do run!!");
    }
}

