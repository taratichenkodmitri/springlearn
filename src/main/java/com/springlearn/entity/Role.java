package com.springlearn.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(EnumSet.of(Permission.STUDENT_READ)),
    TEACHER(EnumSet.of(Permission.STUDENT_READ, Permission.STUDENT_WRITE));
    private final EnumSet<Permission> permissions;

    Role(EnumSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
