package study.heejin.chapter4.item24;

public class LocalClass {
    private String hello = "안녕!";
    private String bye = "잘가!";

    public Greeting greeting() {
        Greeting greeting = new Greeting();
        return greeting;
    }

    class Greeting {
        public Greeting() {
            System.out.println("Greeting 지역 클래스");
        }

        // 사용 불가
        public void hello() {
            System.out.println(LocalClass.this.hello);
        }

        // 사용불가
        public void bye() {
            System.out.println(LocalClass.this.bye);
        }
    }
}
