package chapter3.iterm11;


import chapter3.item10.PhoneNumber;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.cert.CollectionCertStoreParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/22
 * description :
 */
public class HashCodeTest {

    @Test
    @DisplayName("hashMap Collection은 hashCode 를 가지고 조회를 한다")
    public void NotOverrideHashCode() {
        // given
        Map<chapter3.item10.PhoneNumber, String> m = new HashMap();
        m.put(new chapter3.item10.PhoneNumber(707, 867, 5309), "제니");

        // when
        final String actual = m.get(new PhoneNumber(707, 867, 5309));

        // then
        assertThat(actual).isNotEqualTo("제니")
                .as("HashMap 컬렉션은 hash값을 먼저 비교하기 때문에 hash값이 일치하지 않은 위의 값은 비교조차 하지 않는다.")
                .as("그렇기 때문에 equals를 재정의하였다면 hashCode도 재정의하자")
                .isNull();
    }

    @Test
    @DisplayName("hashCode를 재정의한 phoneNumber 검증")
    public void overrideHashCode() {
        // given
        Map<chapter3.item11.PhoneNumber, String> m = new HashMap();
        m.put(new chapter3.item11.PhoneNumber(707, 867, 5309), "제니");

        // when
        final String actual = m.get(new chapter3.item11.PhoneNumber(707, 867, 5309));

        // then
        assertThat(actual).isEqualTo("제니");
    }



    //hashCode 성능비교
    public static void main(String[] args) {
        final Map<chapter3.iterm11.첫번째방식, String> hashMap1 = IntStream.range(0, 10000000)
                .mapToObj(i -> new 첫번째방식("은하철도" + i, i))
                .collect(Collectors.toMap(i -> i, i -> i.getName()));

        final Map<chapter3.iterm11.두번째방식, String> hashMap2 = IntStream.range(0, 10000000)
                .mapToObj(i -> new 두번째방식("은하철도" + i, i))
                .collect(Collectors.toMap(i -> i, i -> i.getName()));

        final Map<chapter3.iterm11.세번째방식, String> hashMap3 = IntStream.range(0, 10000000)
                .mapToObj(i -> new 세번째방식("은하철도" + i, i))
                .collect(Collectors.toMap(i -> i, i -> i.getName()));


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        hashMap1.get(new 첫번째방식("은하철도999", 999));
        stopWatch.stop();

        long result1 = stopWatch.getNanoTime();

        stopWatch.reset();
        stopWatch.start();
        hashMap2.get(new 두번째방식("은하철도999", 999));
        stopWatch.stop();

        long result2 = stopWatch.getNanoTime();

        stopWatch.reset();
        stopWatch.start();

        hashMap3.get(new 세번째방식("은하철도999", 999));
        stopWatch.stop();

        long result3 = stopWatch.getNanoTime();

        System.out.println(String.format("Objects.hashCode() => %s", result1));
        System.out.println(String.format("recommended hashCode => %s", result2));
        System.out.println(String.format("lazy initialization hashCode => %s", result3));
    }
}


class 첫번째방식 {
    private String name;
    private int age;

    public 첫번째방식(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof 첫번째방식)) {
            return false;
        }
        첫번째방식 첫번째방식 = (첫번째방식) o;
        return age == 첫번째방식.age && Objects.equals(name, 첫번째방식.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class 두번째방식 {
    private String name;
    private int age;

    public 두번째방식(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof 두번째방식)) {
            return false;
        }
        두번째방식 두번째방식 = (두번째방식) o;
        return age == 두번째방식.age && Objects.equals(name, 두번째방식.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result += 31 * Integer.hashCode(age);
        return result;
    }
}

class 세번째방식 {
    private String name;
    private int age;

    public 세번째방식(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private int hashCode;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof 세번째방식)) {
            return false;
        }
        세번째방식 세번째방식 = (세번째방식) o;
        return age == 세번째방식.age && Objects.equals(name, 세번째방식.name);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = name.hashCode();
            result += 31 * Integer.hashCode(age);
        }
        return result;
    }
}