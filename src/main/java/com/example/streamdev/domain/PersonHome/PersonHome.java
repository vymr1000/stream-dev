package com.example.streamdev.domain.PersonHome;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class PersonHome {
    @NonNull
    private Integer personId;
    @NonNull
    private Integer homeId;

    @Builder
    public PersonHome(Integer personId, Integer homeId) {
        this.personId = personId;
        this.homeId = homeId;
    }
}
