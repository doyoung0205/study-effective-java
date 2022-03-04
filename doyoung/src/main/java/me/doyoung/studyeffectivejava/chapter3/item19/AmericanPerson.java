package me.doyoung.studyeffectivejava.chapter3.item19;

import java.time.LocalDate;

public class AmericanPerson extends Person {
    public AmericanPerson(LocalDate birthDay, String name) {
        super(birthDay, name);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec 미국 나이를 계산하도록 구현되어있다.
     * @see Person#getBirthDay()
     */
    @Override
    public int getAge() {
        final LocalDate birthDay = super.getBirthDay();
        final LocalDate now = LocalDate.now();
        final LocalDate age = now.minusYears(birthDay.getYear());
        return age.getYear() + 1;
    }

}
