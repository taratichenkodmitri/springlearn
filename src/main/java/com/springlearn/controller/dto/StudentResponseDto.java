package com.springlearn.controller.dto;

import com.springlearn.entity.Student;

public class StudentResponseDto {

    private  Long studentId;

    private String name;

    private Long uin;

    public StudentResponseDto(Long studentId, String name, Long uin) {
        this.studentId = studentId;
        this.name = name;
        this.uin = uin;
    }

    public StudentResponseDto(Student student){
        studentId = student.getStudentId();
        name = student.getName();
        uin = student.getUin();
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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
