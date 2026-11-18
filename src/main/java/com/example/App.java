package com.example;

public final class App {
    private App() {
    }

    public static String greeting() {
        return "CI/CD Experiment 9 is working";
    }

    public static void main(String[] args) {
        System.out.println(greeting());
    }
}
