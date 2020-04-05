package com.eteh.eteh.models;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {

    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
