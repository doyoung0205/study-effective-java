package me.doyoung.studyeffectivejava.chapter3.item19;

import java.time.LocalDate;

public abstract class Person {

    private final LocalDate birthDay;
    private final String name;

    public Person(LocalDate birthDay, String name) {
        this.birthDay = birthDay;
        this.name = name;
    }

    /**
     * 사람의 나이를 반환합니다.
     */
    public abstract int getAge();


    /**
     * 생년월일의 값을 반환합니다.
     */
    public LocalDate getBirthDay() {
        return birthDay;
    }
}
