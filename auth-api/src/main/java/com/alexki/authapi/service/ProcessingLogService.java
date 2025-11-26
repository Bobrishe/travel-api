package com.alexki.authapi.service;

import com.alexki.authapi.entity.ProcessingLog;
import com.alexki.authapi.entity.User;
import com.alexki.authapi.exception.AuthenticationException;
import com.alexki.authapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProcessingLogService {

    private final LogRepository logRepository;
    private final UserService userService;

    @Transactional
    public void createNewLog(String input, String output) {

        UserDetails details = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userService.findUserByEmail(details.getUsername())
                .orElseThrow(() -> new AuthenticationException());

        ProcessingLog log = new ProcessingLog();
        log.setUser(user);
        log.setInputText(input);
        log.setOutputText(output);
        log.setCreated_at(LocalDateTime.now());

        logRepository.save(log);
    }

}
