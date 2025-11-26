package com.alexki.authapi.controller;

import com.alexki.authapi.dto.DataApiResponse;
import com.alexki.authapi.dto.MessageDto;
import com.alexki.authapi.exception.NullMessageException;
import com.alexki.authapi.service.DataApiService;
import com.alexki.authapi.service.ProcessingLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessingController {

    private final DataApiService dataApiService;
    private final ProcessingLogService logService;

    @PostMapping("/process")
    public DataApiResponse process(@RequestBody MessageDto messageDto) throws NullMessageException {

        if (messageDto == null || messageDto.text() == null) {
            throw new NullMessageException();
        }

        String response = dataApiService.request(messageDto);

        logService.createNewLog(messageDto.text(),response);

        return new DataApiResponse("result", response);
    }

}
