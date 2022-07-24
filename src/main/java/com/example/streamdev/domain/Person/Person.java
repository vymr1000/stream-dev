package com.example.streamdev.domain.Person;

import lombok.*;

@Getter
@Setter
@ToString
public class Person {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    @Builder
    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}