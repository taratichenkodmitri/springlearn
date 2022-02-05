package com.springlearn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    private String title;

    private Integer type;

    public School(String title, Integer type) {
        this.title = title;
        this.type = type;
    }

    public School() {

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "School{" +
                "studentId=" + studentId +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
