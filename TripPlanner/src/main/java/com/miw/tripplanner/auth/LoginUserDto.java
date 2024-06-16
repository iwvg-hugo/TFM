package com.miw.tripplanner.auth;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
