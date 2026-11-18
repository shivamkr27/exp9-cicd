package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void greetingIsReturned() {
        assertEquals("CI/CD Experiment 9 is working", App.greeting());
    }
}
