package com.springlearn.service;

import com.springlearn.entity.Job;
import com.springlearn.exception.ExceptionJobNotFound;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.exception.ExceptionTeacherNotFound;
import com.springlearn.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final SchoolService schoolService;
    private final TeacherService teacherService;

    @Autowired
    public JobService(JobRepository jobRepository, SchoolService schoolService, TeacherService teacherService) {
        this.jobRepository = jobRepository;
        this.schoolService = schoolService;
        this.teacherService = teacherService;
    }

    public Long saveJob(Long teacherId, Long schoolId) throws ExceptionTeacherNotFound, ExceptionSchoolNotFound {
        teacherService.findTeacherById(teacherId);
        schoolService.findSchoolById(schoolId);
        return jobRepository.save(new Job(teacherId, schoolId)).getJobId();
    }

    public Job findJobById(Long jobId) throws ExceptionJobNotFound {
        Job job = jobRepository.findById(jobId);
        if (job == null) {
            throw new ExceptionJobNotFound(jobId);
        }
        return job;
    }

    public Job updateJob(Long jobId, Long teacherId, Long schoolId) throws ExceptionJobNotFound {
        Job updatedJob = findJobById(jobId);
        return jobRepository.updateById(updatedJob, new Job(teacherId, schoolId));
    }


    public Job deleteJobById(Long jobId) throws ExceptionJobNotFound {
        Job job = findJobById(jobId);
        return jobRepository.deleteById(job);
    }

    public List<Job> getAllJobForTeacher(Long jobId) {
        return jobRepository.getAllJobsForTeacher(jobId);
    }
}
