package com.alexki.authapi.dto;

import com.alexki.authapi.entity.ProcessingLog;
import com.alexki.authapi.entity.UserRoles;

import java.util.*;

public record UserDto(
        UUID id,
        String email,
        String password,
        UserRoles role,
        List<ProcessingLog> log
) {
}
