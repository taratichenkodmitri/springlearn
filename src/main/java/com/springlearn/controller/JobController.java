package com.springlearn.controller;

import com.springlearn.controller.dto.JobRequestDto;
import com.springlearn.controller.dto.JobResponseDto;
import com.springlearn.exception.ExceptionJobNotFound;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.exception.ExceptionTeacherNotFound;
import com.springlearn.exception.Exceptions;
import com.springlearn.exception.response.ResponseException;
import com.springlearn.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public Long createJob(@RequestBody JobRequestDto jobRequestDto) throws ExceptionTeacherNotFound, ExceptionSchoolNotFound {
        return jobService.saveJob(jobRequestDto.getTeacherId(),
                jobRequestDto.getSchoolId());
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.GET)
    public JobResponseDto getJob(@PathVariable Long jobId) throws ExceptionJobNotFound {
        return new JobResponseDto(jobService.findJobById(jobId));
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.DELETE)
    public JobResponseDto deleteJob(@PathVariable Long jobId) throws ExceptionJobNotFound {
        return new JobResponseDto(jobService.deleteJobById(jobId));
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.PATCH)
    public JobResponseDto updateJob(@PathVariable Long jobId,
                                    @RequestBody JobRequestDto jobRequestDto) throws ExceptionJobNotFound {
        return new JobResponseDto(jobService.updateJob(jobId,
                jobRequestDto.getTeacherId(),
                jobRequestDto.getSchoolId()));
    }

    @RequestMapping(value = "/getAllJobsForTeacher/{teacherId}", method = RequestMethod.GET)
    public List<JobResponseDto> getAllJobsForTeacher(@PathVariable Long teacherId) {
        return jobService.getAllJobForTeacher(teacherId)
                .stream()
                .map(JobResponseDto::new).collect(Collectors.toList());
    }

    @ExceptionHandler({ExceptionTeacherNotFound.class,
            ExceptionSchoolNotFound.class,
            ExceptionJobNotFound.class})
    public ResponseException handleException(Exceptions e) {
        return new ResponseException(e.getMessage());
    }
}
