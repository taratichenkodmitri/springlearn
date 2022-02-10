package com.springlearn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;

    private Long studentId;

    private Long schoolId;

    private Boolean current;

    public Long getEducationId() {
        return educationId;
    }

    public Education() {

    }

    public Education(Long studentId, Long schoolId, Boolean current) {
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.current = current;
    }

    public void setEducationId(Long education) {
        this.educationId = education;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }
}
