package com.springlearn.service;

import com.springlearn.entity.Education;
import com.springlearn.exception.ExceptionAlreadyCurrentEducation;
import com.springlearn.exception.ExceptionCurrentEducationNotFound;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.exception.ExceptionStudentNotFound;
import com.springlearn.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Long saveEducation(Long studentId, Long schoolId, Boolean isCurrent) throws ExceptionStudentNotFound,
            ExceptionSchoolNotFound,
            ExceptionAlreadyCurrentEducation {
        return educationRepository.save(new Education(studentId, schoolId,isCurrent)).getEducationId();
    }

    public Education findEducationById(Long educationId) {
        return educationRepository.findById(educationId);
    }

    public Education updateSchool(Long educationId, Long studentId, Long schoolId, Boolean current) {
        return educationRepository.updateById(educationId, new Education(studentId, schoolId, current));
    }


    public Education deleteEducationById(Long educationId) {
        return educationRepository.deleteById(educationId);
    }

    public Education deleteEducationByStudentId(Long studentId) throws ExceptionCurrentEducationNotFound {
        return educationRepository.deleteByStudentId(studentId);
    }

    public List<Education> getAllEducationsForStudent(Long studentId){
        return educationRepository.getAllEducationsForStudent(studentId);
    }
}
