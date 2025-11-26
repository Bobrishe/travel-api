package com.alexki.authapi.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
