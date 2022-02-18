package me.doyoung.studyeffectivejava.chapter1.item9.trywithresources;


import me.doyoung.studyeffectivejava.chapter1.item9.FileFixture;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TopLine {
    // 코드 9-3 try-with-resources - 자원을 회수하는 최선책! (48쪽)
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(firstLineOfFile(FileFixture.TEXT_FILE));
    }
}
