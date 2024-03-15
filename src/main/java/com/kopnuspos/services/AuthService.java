package com.kopnuspos.services;

import com.kopnuspos.configs.JwtTokenProvider;
import com.kopnuspos.dto.request.LoginRequest;
import com.kopnuspos.dto.response.LoginResponse;
import com.kopnuspos.entitys.UserEntity;
import com.kopnuspos.repositorys.UserReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ResponeService responeService;

    @Autowired
    UserReposotory userReposotory;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<Object> loginService(LoginRequest loginRequest) throws Exception {
        try {
            System.out.println(bCryptPasswordEncoder
                    .encode(loginRequest.getPassword()));
            LoginResponse loginResponse =  new LoginResponse();
            UserEntity userEntity =  userReposotory.findByUsername(loginRequest.getUsername());
            if (userEntity == null)  return new ResponseEntity<>(responeService.ServiceObject(false, "incorrect username or password", null), HttpStatus.NOT_FOUND);
            if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword()))  return new ResponseEntity<>(responeService.ServiceObject(false, "incorrect username or password !", null), HttpStatus.NOT_FOUND);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userEntity.getUserId().toString(),"",new ArrayList<>());
            String accessToken = jwtTokenProvider.generateToken(userEntity.getUserId().toString(), 520000);
            String refreshToken = jwtTokenProvider.generateToken(userEntity.getUserId().toString(), 1020000);

            loginResponse.setAccessToken(accessToken);
            loginResponse.setRefreshToken(refreshToken);
            return new ResponseEntity<>(responeService.ServiceObject(true, "Successfully Login", loginResponse), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Login account failed !");
        }

    }
}
