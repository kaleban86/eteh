package com.eteh.eteh.models;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {

    USER,USER_READING,ADMIN,SUPER_ADMIN,APPEAL_CREATE;

    @Override
    public String getAuthority() {
        return name();
    }


}
