package me.doyoung.studyeffectivejava.chapter12.item90;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Foo implements Serializable {
    public static final Foo INSTANCE = new Foo("FIX");
    private String value;

    public Foo(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private Object writeReplace() {
        return new FooProxy(this);
    }

    private void readObject(ObjectInputStream in){
        throw new UnsupportedOperationException();
    }

    static class FooProxy implements Serializable {
        private String value;

        public FooProxy(Foo foo) {
            this.value = foo.value;
        }

        private Object readResolve(){
            return new Foo(this.value);
        }
    }

}
