package com.alexki.authapi.service;

import com.alexki.authapi.entity.ProcessingLog;
import com.alexki.authapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProcessingLogService {

    private final LogRepository logRepository;

    @Transactional
    public void createNewLog(String input, String output){

        ProcessingLog log = new ProcessingLog();
        log.setInputText(input);
        log.setOutputText(output);
        log.setCreated_at(LocalDateTime.now());

        logRepository.save(log);
    }

}
