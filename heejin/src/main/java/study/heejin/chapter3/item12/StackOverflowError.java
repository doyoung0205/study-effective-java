package study.heejin.chapter3.item12;

public class StackOverflowError {
    public static class A {
        private B b = new B();

        @Override
        public String toString() {
            return "A {" + "b=" + b + "}";
        }
    }

    public static class B {
        private A a = new A();

        @Override
        public String toString() {
            return "B {" + "a=" + a + "}";
        }
    }
}
