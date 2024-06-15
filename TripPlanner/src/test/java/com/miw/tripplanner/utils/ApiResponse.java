package com.miw.tripplanner.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@EqualsAndHashCode
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {

    private final T response;

    @Setter
    private boolean refresh;

    @ConstructorProperties({"response"})
    public ApiResponse(T resp) {
        this.response = resp;
    }

}