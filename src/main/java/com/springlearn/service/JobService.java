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

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Long saveJob(Long teacherId, Long schoolId) throws ExceptionTeacherNotFound, ExceptionSchoolNotFound {
        return jobRepository.save(new Job(teacherId, schoolId)).getJobId();
    }

    public Job findJobById(Long jobId) throws ExceptionJobNotFound {
        return jobRepository.findById(jobId);
    }

    public Job updateJob(Long educationId, Long teacherId, Long schoolId) throws ExceptionJobNotFound {
        return jobRepository.updateById(educationId, new Job(teacherId, schoolId));
    }


    public Job deleteJobById(Long jobId) throws ExceptionJobNotFound {
        return jobRepository.deleteById(jobId);
    }

    public List<Job> getAllJobForTeacher(Long jobId){
        return jobRepository.getAllJobsForTeacher(jobId);
    }
}
