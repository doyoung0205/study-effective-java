package me.doyoung.studyeffectivejava.chapter1.item8.code;

public class BrokenAccount extends Account {
    public BrokenAccount(String name) {
        super(name);
    }

    @Override
    protected void finalize() throws Throwable {
        this.transfer(100000, "dory");
    }
}
