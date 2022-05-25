package chapter8.optional;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * packageName : chapter8.optional
 * fileName : OptionalUtil
 * author : haedoang
 * date : 2022-05-25
 * description :
 */
public class OptionalUtil {
    public static Optional<String> getEmpty() {
        return Optional.empty();
    }

    public static Optional<String> getHello() {
        return Optional.of("hello");
    }

    public static Optional<String> getBye() {
        return Optional.of("bye");
    }

    public static Optional<String> createOptional(String input) {
        if (StringUtils.isEmpty(input) || "empty".equals(input)) {
            return getEmpty();
        }
        return Optional.of(input);
    }
}
