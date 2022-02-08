package com.springlearn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String title;

    private Long type;

    public School(String title, Long type) {
        this.title = title;
        this.type = type;
    }

    public School() {

    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long studentId) {
        this.schoolId = studentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "School{" +
                "studentId=" + schoolId +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
