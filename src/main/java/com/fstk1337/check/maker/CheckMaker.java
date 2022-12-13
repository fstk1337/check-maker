package com.fstk1337.check.maker;

public class CheckMaker {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new CheckMaker().getGreeting());
    }
}
