package study.heejin.chapter7.item45.anagrams;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class HybridAnagrams {
    // args = static/anagrams.txt 2
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(args[0]);
        String path = classPathResource.getFile().getAbsolutePath();

        Path dictionary = Paths.get(path);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(
                    groupingBy(HybridAnagrams::alphabetize)
            ).values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    private static String alphabetize(String word) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
