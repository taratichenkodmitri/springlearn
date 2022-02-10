package com.springlearn.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;


@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    private Long uin;

    private String questionably;

    public Student() {
    }

    public Student(String name, Long uin, String questionably) {
        this.name = name;
        this.uin = uin;
        this.questionably = questionably;
    }

    public String getQuestionably() {
        return questionably;
    }

    public void setQuestionably(String questionably) {
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

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", uin=" + uin +
                ", questionably='" + questionably + '\'' +
                '}';
    }

    public HashMap<String,Object> getQuestionablyFromStringToMap( ) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> hashMap = jsonMapper.readValue(questionably, typeRef);
        return hashMap;
    }
}
