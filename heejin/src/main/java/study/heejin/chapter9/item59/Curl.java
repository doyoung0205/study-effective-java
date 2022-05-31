package study.heejin.chapter9.item59;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Curl {

    // args = https://github.com/pageprologue/study-effective-java/blob/main/README.md
    public static void main(String[] args) throws IOException {
        try (InputStream in = new URL(args[0]).openStream()) {
            in.transferTo(System.out);
        }
    }
}
