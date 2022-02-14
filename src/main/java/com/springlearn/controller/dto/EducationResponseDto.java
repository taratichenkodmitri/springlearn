package com.springlearn.controller.dto;

import com.springlearn.entity.Education;

public class EducationResponseDto {

    private Long educationId;

    private Long studentId;

    private Long schoolId;

    private Boolean current;

    public EducationResponseDto(Education education) {
        educationId = education.getEducationId();
        studentId = education.getStudentId();
        schoolId = education.getSchoolId();
        current = education.getCurrent();
    }

    public EducationResponseDto(Long educationId, Long studentId, Long schoolId, Boolean current) {
        this.educationId = educationId;
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.current = current;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
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
