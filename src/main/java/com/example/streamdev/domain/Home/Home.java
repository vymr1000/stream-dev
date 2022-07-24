package com.example.streamdev.domain.Home;

import lombok.*;

@Getter
@Setter
@ToString
public class Home {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    @Builder
    public Home(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}