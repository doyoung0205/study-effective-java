package me.doyoung.studyeffectivejava.chapter9.item59;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// 코드 59-2 transferTo 메서드를 이용해 URL의 내용 가져오기 - 자바 9부터 가능하다. (353쪽)
public class Curl {
    public static void main(String[] args) throws IOException {
        try (InputStream in = new URL(args[0]).openStream()) {
            in.transferTo(System.out);
        }
    }
}
