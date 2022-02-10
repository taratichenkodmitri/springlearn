package com.springlearn.controller.dto;

import com.springlearn.entity.Teacher;

public class TeacherResponseDto {
    private  Long teacherId;

    private String name;

    private Long uin;

    public TeacherResponseDto(Long teacherId, String name, Long uin) {
        this.teacherId = teacherId;
        this.name = name;
        this.uin = uin;
    }

    public TeacherResponseDto(Teacher teacher) {
        teacherId = teacher.getTeacherId();
        name = teacher.getName();
        uin = teacher.getUin();
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

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
