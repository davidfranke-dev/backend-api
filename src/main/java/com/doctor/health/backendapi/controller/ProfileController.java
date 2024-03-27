package com.doctor.health.backendapi.controller;


import com.doctor.health.backendapi.authentication.models.User;
import com.doctor.health.backendapi.authentication.repository.UserRepository;
import com.doctor.health.backendapi.authentication.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Value("${doctor.health.app.jwtCookieName}")
    private String jwtCookie;

    @GetMapping
    public ResponseEntity<Optional<User>> getProfile(HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromCookies(request);
        String email = jwtUtils.getUserNameFromJwtToken(jwt);
        System.out.println(email);
        return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
    }

}
