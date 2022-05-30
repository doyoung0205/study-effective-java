package chapter8.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static chapter8.optional.OptionalUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * packageName : chapter8.optional
 * fileName : OptionalTest
 * author : haedoang
 * date : 2022-05-25
 * description :
 */
public class OptionalTest {

    @Test
    @DisplayName("빈 Optional 객체 생성하기")
    public void createEmpty() {
        // given
        Optional<Object> actual = Optional.empty();

        // then
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("값을 보장하는 Optional 생성하기")
    public void nonNullCreateOptional() {
        // given
        String name = "haedoang";

        // when
        Optional<String> actual = Optional.of(name);

        // then
        assertTrue(actual.isPresent());
    }

    @Test
    @DisplayName("Optional.of() null 값은 예외를 던진다")
    public void givenNullThrowException() {
        // given
        String name = null;

        // then

        Assertions.assertThrows(NullPointerException.class, () -> Optional.of(name));
    }

    @Test
    @DisplayName("null값이 올 수 있다면 Optional.ofNullable()을 사용하라")
    public void givenNullValue() {
        // given
        String name = null;

        // when
        Optional<String> actual = Optional.ofNullable(name);

        // then
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("isPresent 비교 테스트")
    public void isPresent() {
        // given
        Optional<String> valueOptional = Optional.of("haedoang");
        Optional<Object> emptyOptional = Optional.empty();
        Optional<Object> nullValueOptional = Optional.ofNullable(null);

        // then
        assertTrue(valueOptional.isPresent());
        assertFalse(emptyOptional.isPresent());
        assertFalse(nullValueOptional.isPresent());
    }

    @Test
    @DisplayName("isEmpty 비교 테스트")
    public void isEmpty() {
        // given
        Optional<String> valueOptional = Optional.of("haedoang");
        Optional<Object> emptyOptional = Optional.empty();
        Optional<Object> nullValueOptional = Optional.ofNullable(null);

        // then
        assertFalse(valueOptional.isEmpty());
        assertTrue(emptyOptional.isEmpty());
        assertTrue(nullValueOptional.isEmpty());
    }

    @Test
    @DisplayName("명시적 null처리 테스트")
    public void handlingNullValue() {
        // given
        Optional<String> str = Optional.of("haedoang");

        // then
        assertTrue(str.isPresent());

        str.ifPresent(it -> {
            assertThat(it.toUpperCase()).isEqualTo("HAEDOANG");
        });
    }

    @Test
    @DisplayName("기본값을 지원하는 orElse() 테스트")
    public void defaultValueWithOrElse() {
        // given
        String nullName = null;

        // when
        String name = Optional.ofNullable(nullName).orElse("haedoang");

        // then
        assertThat(name).isEqualTo("haedoang");
    }

    @Test
    @DisplayName("반환값값 대신 공급자 지원하는 orElseGet() 테스트")
    public void defaultValueWithOrElseGet() {
        // given
        String nullName = null;

        // when
        String name = Optional.ofNullable(nullName)
                .orElseGet(() -> "haedoang");

        // then
        assertThat(name).isEqualTo("haedoang")
                .as("Lazy Evaluation");
    }

    @Test
    @DisplayName("부재값을 처리하는 orElseThrow() 테스트")
    public void orElseThrow() {
        // given
        String nullName = null;

        // then
        assertThatThrownBy(() -> {
            Optional.ofNullable(nullName)
                    .orElseThrow(IllegalArgumentException::new);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("return 값을 보장하는 get()")
    public void get() {
        // given
        Optional<Object> nullName = Optional.ofNullable(null);
        Optional<String> optional = Optional.of("haedoang");

        // when
        String name = optional.get();

        // then
        assertThat(name).isEqualTo("haedoang");

        assertThatThrownBy(() -> nullName.get())
                .isInstanceOf(NoSuchElementException.class)
                .as("객체가 null인경우 NoSuchElelmentException이 발생한다");
    }

    @Test
    @DisplayName("조건부 반환 filter() 테스트")
    public void conditionalReturnFilter() {
        // given
        Optional<String> name = Optional.of("haedoang");

        // when
        boolean hasChar_A = name.filter(it -> it.contains("a")).isPresent();
        boolean hasChar_Z = name.filter(it -> it.contains("z")).isPresent();

        // then
        assertTrue(hasChar_A);
        assertFalse(hasChar_Z);
    }

    @Test
    @DisplayName("값 변환 map() 테스트")
    public void transformingValue() {
        // given
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        // when
        int size = listOptional
                .map(List::size)
                .orElse(0);

        // then
        assertThat(size).isEqualTo(6);
    }


    @Test
    @DisplayName("map(), filter() 체이닝")
    public void mapWithFilter() {
        // given
        String password = " admin1234 ";
        Optional<String> optionalPassword = Optional.of(password);

        // when
        boolean checkPassword =
                optionalPassword.filter(it -> it.equals("admin1234"))
                        .isPresent();

        // then
        assertFalse(checkPassword);

        // when
        boolean checkPasswordWithTrim = optionalPassword
                .map(String::trim)
                .filter(it -> it.equals("admin1234"))
                .isPresent();

        // then
        assertTrue(checkPasswordWithTrim);
    }

    @Test
    @DisplayName("값 변환 flatMap() 테스트")
    public void transformingWithFlatMap() {
        // given
        Person person = new Person("haedoang", 34);
        Person noNamePerson = new Person(null, 34);
        Optional<Person> personOptional = Optional.of(person);
        Optional<Person> nullPerson = Optional.ofNullable(null);
        Optional<Person> noNamePersonOptional = Optional.of(noNamePerson);


        // when
        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(() -> new IllegalArgumentException("name이 없습니다"));
        String name = nameOptional.orElseThrow(() -> new IllegalArgumentException("name이 없습니다"));

        // then
        assertThat(name).isEqualTo("haedoang")
                .as("optional wrapping인 경우 값을 도출하는데 위와 같은 과정이 필요하다");

        // when
        String name2 = personOptional.flatMap(Person::getName)
                .orElseThrow(() -> new IllegalStateException("값이 존재하지 않습니다."));

        // then
        assertThat(name2).isEqualTo("haedoang");


        assertThatThrownBy(() -> {
            nullPerson.flatMap(Person::getName)
                    .orElseThrow(() -> new IllegalStateException("값이 존재하지 않습니다."));

            noNamePersonOptional.flatMap(Person::getName)
                    .orElseThrow(() -> new IllegalStateException("값이 존재하지 않습니다."));
        })
                .as("값이 존재하지 않는 예외 처리 테스트")
                .as("예외처리를 한번에 할 수 있음");
    }

    @Test
    @DisplayName("옵셔널 체이닝 테스트")
    public void optionalChaining() {
        // given
        Optional<String> actual = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent) //false true true
                .map(Optional::get) // hello, bye
                .findFirst();// hello

        // then
        assertThat(actual).isEqualTo(getHello())
                .as("스트림이 비어있지 않은 경우 get()이 모두 호출되는 단점이 있음");
    }

    @Test
    @DisplayName("lazy evaluate 옵셔널 체이닝 테스트")
    public void optionalChainingWithSupplier() {
        // when
        Optional<String> actual = Stream.<Supplier<Optional<String>>>of(
                        OptionalUtil::getEmpty,
                        OptionalUtil::getHello,
                        OptionalUtil::getBye
                ).map(Supplier::get)            //<Optional>empty <Optional>getHello <Optional>bye
                .filter(Optional::isPresent)    //<Optional>getHello <Optional>bye
                .map(Optional::get) //hello bye
                .findFirst();       //hello

        // then
        assertThat(actual).isEqualTo(getHello());

        // when
        String defaultVal = Stream.<Supplier<Optional<String>>>of(
                        OptionalUtil::getEmpty,
                        OptionalUtil::getEmpty,
                        OptionalUtil::getEmpty
                ).map(Supplier::get)            //<Optional>empty <Optional>getHello <Optional>bye
                .filter(Optional::isPresent)    //<Optional>getHello <Optional>bye
                .map(Optional::get) //hello bye
                .findFirst()
                .orElseGet(() -> "default");

        // then
        assertThat(defaultVal).isEqualTo("default");
    }
}
