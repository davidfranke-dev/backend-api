package com.doctor.health.backendapi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.doctor.health.backendapi.authentication.security.services.UserDetailsImpl;
import com.doctor.health.backendapi.authentication.models.ERole;
import com.doctor.health.backendapi.authentication.models.Role;
import com.doctor.health.backendapi.authentication.models.User;
import com.doctor.health.backendapi.authentication.payload.request.LoginRequest;
import com.doctor.health.backendapi.authentication.payload.request.SignupRequest;
import com.doctor.health.backendapi.authentication.payload.response.MessageResponse;
import com.doctor.health.backendapi.authentication.payload.response.UserInfoResponse;
import com.doctor.health.backendapi.authentication.repository.RoleRepository;
import com.doctor.health.backendapi.authentication.repository.UserRepository;
import com.doctor.health.backendapi.authentication.security.jwt.JwtUtils;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;  

    @Autowired 
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) { 

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

      List<String> roles = userDetails.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList());

      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()) 
          .body(new UserInfoResponse(userDetails.getId(),
                                    userDetails.getUsername(),
                                    roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

      // Method to check if user does already exist
  
      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Email is already in use!"));
      }
  
      // Create new user's account
      User user = new User(signUpRequest.getEmail(),
                           encoder.encode(signUpRequest.getPassword()));
  
      Set<String> strRoles = signUpRequest.getRoles();
      Set<Role> roles = new HashSet<>();
  
      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
  
            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
  
            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          }
        });
      }
  
      user.setRoles(roles);
      userRepository.save(user);
  
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
