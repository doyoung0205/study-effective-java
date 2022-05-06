package study.heejin.chapter7.item46;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Freq {
    // args = static/anagrams.txt
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(args[0]);
        File file = classPathResource.getFile();

        // 스트림 패러다임을 이해하지 못한 채 API만 사용한 잘못된 사용법
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }

        System.out.println(freq);

        // 스트림을 제대로 사용
        Map<String, Long> freq2;
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq2 = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        System.out.println(freq2);

        // 가장 흔한 단어 10개를 뽑아내는 파이프라인
        List<String> top3 = freq2.keySet().stream()
                .sorted(comparing(freq2::get).reversed())
                .limit(3)
                .collect(toList());

        System.out.println(top3);
    }
}
