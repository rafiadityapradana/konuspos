package com.kopnuspos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Setter
@Getter
public class LoginRequest {

    @NotBlank(message = "Username is required")
    @Size(max = 70, message = "UserName must be at most 70 characters")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
