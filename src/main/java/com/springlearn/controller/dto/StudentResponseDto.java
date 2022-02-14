package com.springlearn.controller.dto;

import com.springlearn.entity.Student;

import java.io.IOException;
import java.util.HashMap;

public class StudentResponseDto {

    private Long studentId;

    private String name;

    private Long uin;

    HashMap<String, Object> questionably;

    public StudentResponseDto(Long studentId, String name, Long uin, HashMap<String, Object> questionably) {
        this.studentId = studentId;
        this.name = name;
        this.uin = uin;
        this.questionably = questionably;
    }

    public StudentResponseDto(Student student) throws IOException {
        studentId = student.getStudentId();
        name = student.getName();
        uin = student.getUin();
        questionably = student.getQuestionablyFromStringToMap();
    }

    public HashMap<String, Object> getQuestionably() {
        return questionably;
    }

    public void setQuestionably(HashMap<String, Object> questionably) {
        this.questionably = questionably;
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
