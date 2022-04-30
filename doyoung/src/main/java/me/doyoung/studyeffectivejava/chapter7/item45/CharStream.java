package me.doyoung.studyeffectivejava.chapter7.item45;

// char 데이터를 처리할 때는 스트림 사용을 자제하자. (272쪽)
public class CharStream {
    public static void main(String[] args) {
        // 예상한 결과와 다르다.
        "Hello world!".chars().forEach(System.out::print);
        System.out.println();

        // 문제를 고치려면 형변환을 명시적으로 해줘야 한다.
        "Hello world!".chars().forEach(x -> System.out.print((char) x));
        System.out.println();
    }
}
