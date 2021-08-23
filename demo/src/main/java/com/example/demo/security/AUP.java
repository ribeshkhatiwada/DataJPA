package com.example.demo.security;

public enum AUP {
    STUDENT_COPY("student:copy"),
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    AUP(String permission) {
    this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
