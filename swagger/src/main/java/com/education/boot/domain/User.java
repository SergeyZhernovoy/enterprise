package com.education.boot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    @JsonProperty
    private final String userName;
    @NonNull
    @JsonProperty
    private final Integer salary;
}
