package com.springlearn.controller.dto;

public class TeacherRequestDto {
    private String name;

    private Long uin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUin() {
        return uin;
    }

    public void setUin(Long uin) {
        this.uin = uin;
    }
}
