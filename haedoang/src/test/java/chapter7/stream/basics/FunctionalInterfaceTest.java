package chapter7.stream.basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.stream.basics
 * fileName : FunctionalInterfaceTest
 * author : haedoang
 * date : 2022-05-19
 * description :
 */
public class FunctionalInterfaceTest {

    @Test
    @DisplayName("")
    public void test() {
        // given
        Map<String, Integer> nameMap = new HashMap<>();
        //nameMap.put("John", 10);

        // when
        int value = nameMap.computeIfAbsent("John", String::length);

        // then
        assertThat(value).isEqualTo(4);

        //TODO 하자 
    }
}
