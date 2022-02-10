package com.springlearn.service;

import com.springlearn.entity.School;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Long saveSchool(String title, Long type) {
        return schoolRepository.save(new School(title, type)).getSchoolId();
    }

    public School findSchoolById(Long schoolId) throws ExceptionSchoolNotFound {
        return schoolRepository.findById(schoolId);
    }

    public School updateSchool(Long schoolId, String title, Long type) throws ExceptionSchoolNotFound {
        return schoolRepository.updateById(schoolId, new School(title, type));
    }

    public School deleteSchoolById(Long schoolId) throws ExceptionSchoolNotFound {
        return schoolRepository.deleteById(schoolId);
    }
}
