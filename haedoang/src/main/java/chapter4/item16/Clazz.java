package chapter4.item16;

/**
 * author : haedoang
 * date : 2022/03/01
 * description :
 */
public class Clazz {
    int a = 1;
    static int b = 2;

    public static class StaticNestClass {
        int a = 3;
        static int b = 4;

        private void run() {
            System.out.println(String.format("%s's method invoke", getClass().getSimpleName()));
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            //System.out.println("Clazz.this.a = " + Clazz.this.a);
            System.out.println("Clazz.b = " + Clazz.b);
            //System.out.println("Clazz.this.b = " + Clazz.this.b);
            System.out.println("static 중첩 클래스는 정적 멤버만 접근 가능하다");
            System.out.println("--------------------------------------------------------------");
        }
    }

    public class InnerClass {
        int a = 3;
        //static int b = 4;

        private void run() {
            System.out.printf("%s's method invoke%n", getClass().getSimpleName());
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("Clazz.this.a = " + Clazz.this.a);
            System.out.println("Clazz.b = " + Clazz.b);
            System.out.println("내부 클래스는 정적/비정적 멤버 모두 접근 가능하다");
            System.out.println("--------------------------------------------------------------");
        }
    }

    public class NotUsefulInnerClass {
        int a = 3;
        int b = 4;

        public void run() {
            System.out.println("a = " + a);
            System.out.println("b = " + b);
        }
    }

    public void callLocalClassMethod() {
        class Local {
            private int a = 3;
            //private static int b = 4;

            void run () {
                System.out.printf("%s's method invoke%n", getClass().getSimpleName());
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("Clazz.this.a = " + Clazz.this.a);
                System.out.println("Clazz.b = " + Clazz.b);
                System.out.println("로컬 클래스는 정적/비정적 멤버 모두 접근 가능하다");
                System.out.println("--------------------------------------------------------------");
            }
        }
        new Local().run();
    }

    public void callAnonymousClassMethod() {
        new SimpleAbstractClass() {
            private int a = 3;
            //private static int b = 4;

            @Override
            void run() {
                System.out.printf("AnonymousClass's method invoke%n", getClass().getSimpleName());
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("Clazz.this.a = " + Clazz.this.a);
                System.out.println("Clazz.b = " + Clazz.b);
                System.out.println("내부 클래스는 정적/비정적 멤버 모두 접근 가능하다");
                System.out.println("--------------------------------------------------------------");
            }
        }.run();
    }

    public void callAnonymousClassMethod2() {
        new SimpleInterface() {
            private int a = 3;
            //private static int b = 4;

            @Override
            public void run() {
                System.out.println(String.format("AnonymousClass's method invoke", getClass().getSimpleName()));
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("Clazz.this.a = " + Clazz.this.a);
                System.out.println("Clazz.b = " + Clazz.b);
                System.out.println("내부 클래스는 정적/비정적 멤버 모두 접근 가능하다");
                System.out.println("--------------------------------------------------------------");
            }
        }.run();
    }

    public static void main(String[] args) {
        System.out.println("NestStaticClass test!");
        Clazz.StaticNestClass staticNestClass = new Clazz.StaticNestClass();
        staticNestClass.run();

        System.out.println("InnerClass test!");
        Clazz clazz = new Clazz();
        Clazz.InnerClass inner = clazz.new InnerClass();
        inner.run();

        System.out.println("localClass test!");
        new Clazz().callLocalClassMethod();

        System.out.println("anonymousClass test!");
        new Clazz().callAnonymousClassMethod();

        System.out.println("anonymousClass2 test!");
        new Clazz().callAnonymousClassMethod2();
    }
}


abstract class SimpleAbstractClass {
    abstract void run();
}

interface SimpleInterface {
    void run();
}

