package me.doyoung.studyeffectivejava.chapter2.item11;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class HashSpeedTest {

    @Test
    void normal() {
        final Person person = new Person("DORY") {
            @Override
            public int hashCode() {
                int result;
                long temp;
                result = super.id != null ? super.id.hashCode() : 0;
                result = 31 * result + super.age;
                result = 31 * result + (int) super.grade;
                temp = Double.doubleToLongBits(super.money);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                result = 31 * result + (super.name != null ? super.name.hashCode() : 0);
                return result;
            }
        };

        checkSpeed(person);
    }

    @Test
    void objectsUtils() {
        final Person person = new Person("DORY") {
            @Override
            public int hashCode() {
                return Objects.hash(super.name, super.age, super.id, super.money, super.grade);
            }
        };
        checkSpeed(person);
    }

    @Test
    void withLazyAndCache() {
        final Person person = new Person("DORY") {
            private int hashCode;

            @Override
            public int hashCode() {
                int result = hashCode;
                if (result == 0) {
                    long temp;
                    result = super.id != null ? super.id.hashCode() : 0;
                    result = 31 * result + super.age;
                    result = 31 * result + (int) super.grade;
                    temp = Double.doubleToLongBits(super.money);
                    result = 31 * result + (int) (temp ^ (temp >>> 32));
                    result = 31 * result + (super.name != null ? super.name.hashCode() : 0);
                    hashCode = result;
                }
                return result;

            }
        };

        checkSpeed(person);
    }

    private void checkSpeed(Person person) {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 100_000_000; i++) {
            person.hashCode();
        }
        long et = System.currentTimeMillis();

        System.out.println((et - st) + " ms");
    }

    static abstract class Person {
        private Long id;
        private int age;
        private short grade;
        private double money;
        private String name;


        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            if (grade != person.grade) return false;
            if (Double.compare(person.money, money) != 0) return false;
            if (!Objects.equals(id, person.id)) return false;
            return Objects.equals(name, person.name);
        }

        @Override
        public abstract int hashCode();

    }
}
