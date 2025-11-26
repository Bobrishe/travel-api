package com.alexki.authapi.service;

import com.alexki.authapi.dto.MessageDto;
import com.alexki.authapi.exception.NullMessageException;
import com.alexki.authapi.property.DataApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class DataApiService {

    private final DataApiProperties dataApiProperties;

    public String request(MessageDto messageDto) throws NullMessageException {

        try {
            return RestClient.builder()
                    .baseUrl(dataApiProperties.getUrl())
                    .build()
                    .post()
                    .uri("/api/transform")
                    .header("X-Internal-Token", dataApiProperties.getToken())
                    .body(messageDto.text())
                    .retrieve()
                    .body(String.class);

        } catch (Exception e) {
            throw new NullMessageException();
        }


    }
}
