package study.heejin.chapter8.item55;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamOptional {

    public static void main(String[] args) {
        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.empty(), Optional.of("foo"), Optional.empty(), Optional.of("bar")
        );

        // 자바 8
        listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);

        System.out.println("------------------");

        // 자바 9
        listOfOptionals.stream()
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}
