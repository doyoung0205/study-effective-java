package study.heejin.chapter7.item45.anagrams;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class StreamAnagrams {
    // args = static/anagrams.txt 2
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(args[0]);
        String path = classPathResource.getFile().getAbsolutePath();

        Path dictionary = Paths.get(path);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(
                    groupingBy(word -> word.chars()
                            .sorted()
                            .collect(
                                    StringBuilder::new,
                                    (sb, c) -> sb.append((char) c),
                                    StringBuilder::append
                            ).toString()
                    )
            ).values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
}
