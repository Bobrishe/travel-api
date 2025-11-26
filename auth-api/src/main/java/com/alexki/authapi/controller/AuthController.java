package com.alexki.authapi.controller;

import com.alexki.authapi.dto.UserDto;
import com.alexki.authapi.entity.User;
import com.alexki.authapi.exception.AuthenticationException;
import com.alexki.authapi.exception.UserExistException;
import com.alexki.authapi.mapper.UserMapper;
import com.alexki.authapi.service.JwtService;
import com.alexki.authapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserDto userDto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());

        try {
            authenticationManager.authenticate(auth);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException();
        }

        UserDetails userDetails = userService.loadUserByUsername(userDto.email());

        return jwtService.generateToken(userDetails);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDto userDto) {

        User user = userMapper.toEntity(userDto);

        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserExistException();
        }

        userService.createUser(user);

    }
}
