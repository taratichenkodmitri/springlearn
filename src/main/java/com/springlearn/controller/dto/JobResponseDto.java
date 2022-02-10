package com.springlearn.controller.dto;

import com.springlearn.entity.Job;

public class JobResponseDto {
    private Long jobId;

    private Long teacherId;

    private Long schoolId;

    public JobResponseDto(Job job) {
        jobId = job.getJobId();
        teacherId = job.getTeacherId();
        schoolId = job.getSchoolId();
    }

    public JobResponseDto(Long jobId, Long teacherId, Long schoolId) {
        this.jobId = jobId;
        this.teacherId = teacherId;
        this.schoolId = schoolId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
