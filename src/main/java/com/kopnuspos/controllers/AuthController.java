package com.kopnuspos.controllers;

import com.kopnuspos.dto.request.LoginRequest;
import com.kopnuspos.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<Object> loginController(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
        return authService.loginService(loginRequest);
    }


}
