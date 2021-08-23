package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.AUP.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ,COURSE_READ,STUDENT_COPY));

    private final Set<AUP> per;

    ApplicationUserRole(Set<AUP> permission) {
        this.per = permission;
    }

    public Set<AUP> getP() {
        return per;
    }

    public Set<GrantedAuthority> getGrantedAuthority(){
       Set<GrantedAuthority> permissions =  getP().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
                return permissions;
    }
}
