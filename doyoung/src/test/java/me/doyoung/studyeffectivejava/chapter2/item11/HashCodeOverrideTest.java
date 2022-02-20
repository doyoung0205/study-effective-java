package me.doyoung.studyeffectivejava.chapter2.item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class HashCodeOverrideTest {

    @DisplayName("hashcode 를 override 하지 않으면 " +
            "equals 가 true 인 같은 객체이지만 " +
            " HashMap 에서 같은 Key 로 사용될 수 없다.")
    @Test
    void notHashcodeWithMap() {
        // given
        final PersonWithOnlyEquals doyoung = new PersonWithOnlyEquals("doyoung");
        final PersonWithOnlyEquals doyoung2 = new PersonWithOnlyEquals("doyoung");

        assertEquals(doyoung, doyoung2); // 두 객체는 같은 객체 !

        HashMap<PersonWithOnlyEquals, String> christmasMap = new HashMap<>();
        christmasMap.put(doyoung, "크리스마스선물");

        final String result = christmasMap.get(doyoung2);

        assertNull(result);
    }

    @DisplayName("hashcode 를 override 하지 않으면 " +
            "equals 가 true 인 같은 객체이지만 " +
            " HashSet 같은 객체로 판별하지 않는다.")
    @Test
    void notHashcodeWithSet() {
        // given
        final PersonWithOnlyEquals doyoung = new PersonWithOnlyEquals("doyoung");
        final PersonWithOnlyEquals doyoung2 = new PersonWithOnlyEquals("doyoung");

        assertEquals(doyoung, doyoung2); // 두 객체는 같은 객체 !

        HashSet<PersonWithOnlyEquals> christmasSet = new HashSet<>();
        christmasSet.add(doyoung);

        final boolean result = christmasSet.contains(doyoung2);

        assertFalse(result);
    }

    @DisplayName("hashcode 를 override 하면 " +
            "equals 가 true 인 같은 객체이지만 " +
            " HashMap 에서 같은 Key 로 사용될 수 있다.")
    @Test
    void hashCodeWithMap() {
        final PersonWithEqualsAndHashCode doyoung = new PersonWithEqualsAndHashCode("doyoung");
        final PersonWithEqualsAndHashCode doyoung2 = new PersonWithEqualsAndHashCode("doyoung");

        assertEquals(doyoung, doyoung2); // 두 객체는 같은 객체 !

        HashMap<PersonWithEqualsAndHashCode, String> christmasMap = new HashMap<>();
        christmasMap.put(doyoung, "크리스마스선물");

        final String result = christmasMap.get(doyoung2);

        assertNotNull(result);
        assertEquals(result, "크리스마스선물");
    }


    @DisplayName("hashcode 를 override 하면" +
            "equals 가 true 인 같은 객체이지만 " +
            " HashSet 같은 객체로 판별한다.")
    @Test
    void hashcodeWithSet() {
        // given
        final PersonWithEqualsAndHashCode doyoung = new PersonWithEqualsAndHashCode("doyoung");
        final PersonWithEqualsAndHashCode doyoung2 = new PersonWithEqualsAndHashCode("doyoung");

        assertEquals(doyoung, doyoung2); // 두 객체는 같은 객체 !

        HashSet<PersonWithEqualsAndHashCode> christmasSet = new HashSet<>();
        christmasSet.add(doyoung);

        final boolean result = christmasSet.contains(doyoung2);

        assertTrue(result);
    }

    static class PersonWithOnlyEquals {
        private String name;

        public PersonWithOnlyEquals(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonWithOnlyEquals)) return false;

            PersonWithOnlyEquals that = (PersonWithOnlyEquals) o;

            return Objects.equals(name, that.name);
        }
    }

    static class PersonWithEqualsAndHashCode {
        private String name;

        public PersonWithEqualsAndHashCode(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonWithEqualsAndHashCode)) return false;

            PersonWithEqualsAndHashCode that = (PersonWithEqualsAndHashCode) o;

            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
