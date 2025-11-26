package com.alexki.dataapi.controller;

import com.alexki.dataapi.exception.NullMessageException;
import com.alexki.dataapi.exception.WrongSecretKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @PostMapping("transform")
    public String transformString(@RequestBody String text,
                                              @RequestHeader("X-Internal-Token") String token,
                                              @Value("${INTERNAL_TOKEN}") String expectedToken) throws Exception {

        if (!token.equals(expectedToken)) {
            throw new WrongSecretKeyException();
        }

        if (text == null || text.isBlank()) {
            throw new NullMessageException();
        }

        StringBuilder sb = new StringBuilder(text);

        return sb.reverse().toString().toUpperCase();
    }

}
