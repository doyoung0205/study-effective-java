package me.doyoung.studyeffectivejava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Lamdba {
    @Test
    void supplyExample() {
        assertThrows(
                NumberFormatException.class,
                () -> Integer.parseInt("One"),
                "NumberFormatException was expected");
    }
}
