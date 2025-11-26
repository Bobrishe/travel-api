package com.alexki.authapi.mapper;

import com.alexki.authapi.dto.UserDto;
import com.alexki.authapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toEntity(UserDto dto) {
        return new User(
                dto.id(),
                dto.email(),
                dto.password(),
                dto.role(),
                dto.log()
        );
    }

}
