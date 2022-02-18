package me.doyoung.studyeffectivejava.chapter1.item9.trywithresources;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static me.doyoung.studyeffectivejava.chapter1.item9.FileFixture.TEXT_FILE;

public class TopLineWithDefault {
    // 코드 9-5 try-with-resources를 catch 절과 함께 쓰는 모습 (49쪽)
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(firstLineOfFile(TEXT_FILE, "Toppy McTopFace"));
    }
}
