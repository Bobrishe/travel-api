package com.alexki.dataapi.controller;

import com.alexki.dataapi.dto.MessageDto;
import com.alexki.dataapi.exception.NullMessageException;
import com.alexki.dataapi.exception.WrongSecretKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout() {
        return "logout";
    }

    @PostMapping("transform")
    public String transformString(@RequestBody MessageDto messageDto,
                                  @RequestHeader("X-Internal-Token") String token,
                                  @Value("${env.x-internal-token}") String expectedToken) throws Exception {

        if (!token.equals(expectedToken)) {
            throw new WrongSecretKeyException();
        }

        if (messageDto.text() == null || messageDto.text().isBlank()) {
            throw new NullMessageException();
        }

        StringBuilder sb = new StringBuilder(messageDto.text());

        return sb.reverse().toString().toUpperCase();
    }

}
