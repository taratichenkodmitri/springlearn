package com.springlearn.controller.dto;

import com.springlearn.entity.School;

public class SchoolResponseDto {

    private Long schoolId;

    private String title;

    private Long type;

    public SchoolResponseDto(School school) {
        schoolId = school.getSchoolId();
        title = school.getTitle();
        type = school.getType();
    }

    public SchoolResponseDto(Long schoolId, String title, Long type) {
        this.schoolId = schoolId;
        this.title = title;
        this.type = type;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
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
}
