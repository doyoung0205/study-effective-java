package study.heejin.chapter7.item45.anagrams;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class IterativeAnagrams {
    // staple -> aelpst, petals -> aelpst
    // args = static/anagrams.txt 2
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(args[0]);
        String path = classPathResource.getFile().getAbsolutePath();
        int minGroupSize = Integer.parseInt(args[1]);

        Map<String, Set<String>> groups = new HashMap<>();
        File dictionary = new File(path);

        try (Scanner sc = new Scanner(dictionary)) {
            while (sc.hasNext()) {
                String word = sc.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>())
                        .add(word);
            }
        }

        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize) {
                System.out.println(group.size() + ": " + group);
            }
        }
    }

    private static String alphabetize(String word) {
        char[] c = word.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}
