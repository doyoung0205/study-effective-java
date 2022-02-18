package me.doyoung.studyeffectivejava.chapter1.item9.trywithresources;

import me.doyoung.studyeffectivejava.chapter1.item9.FileFixture;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;

    // 코드 9-4 복수의 자원을 처리하는 try-with-resources - 짧고 매혹적이다! (49쪽)
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }

    public static void main(String[] args) throws IOException {
        copy(FileFixture.TEXT_FILE, FileFixture.TEXT_DIR + LocalDateTime.now());
    }
}
