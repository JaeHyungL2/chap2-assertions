package com.ohgiraffers.assertions.section01.jupiter;

public class Person {

    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    // 알트+인서트로 생성자 한방에 게터세터
}
