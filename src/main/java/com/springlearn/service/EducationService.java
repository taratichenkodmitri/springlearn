package com.springlearn.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlearn.entity.Education;
import com.springlearn.entity.School;
import com.springlearn.entity.Student;
import com.springlearn.exception.*;
import com.springlearn.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    private final StudentService studentService;

    private final SchoolService schoolService;

    @Autowired
    public EducationService(EducationRepository educationRepository,
                            StudentService studentService,
                            SchoolService schoolService) {
        this.educationRepository = educationRepository;
        this.studentService = studentService;
        this.schoolService = schoolService;
    }

    public Long saveEducation(Long studentId, Long schoolId, Boolean isCurrent) throws ExceptionStudentNotFound,
            ExceptionSchoolNotFound,
            ExceptionAlreadyCurrentEducation, IOException, ExceptionNotValidQuestionably {
        Student student = studentService.findStudentById(studentId);
        School school = schoolService.findSchoolById(schoolId);

       List<String> listQuestionCodes = educationRepository.getQuestionCodes(school.getType());

        ObjectMapper jsonMapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> questionably = jsonMapper.readValue(student.getQuestionably(), typeRef);

        for (String key : questionably.keySet()) {
            if(!listQuestionCodes.contains(key)) {
                throw new ExceptionNotValidQuestionably(key);
            }
        }

        List<Education> allEducationsForStudent = getAllEducationsForStudent(studentId);
        for(Education ed: allEducationsForStudent){
            if(ed.getCurrent()) {
                throw new ExceptionAlreadyCurrentEducation(ed.getSchoolId());
            }
        }

        return educationRepository.save(new Education(studentId, schoolId, isCurrent)).getEducationId();
    }

    public Education findEducationById(Long educationId) throws ExceptionEducationNotFound {
        Education education = educationRepository.findById(educationId);
        if(education == null) {
            throw new ExceptionEducationNotFound(educationId);
        }
        return education;
    }

    public Education updateEducation(Long educationId, Long studentId, Long schoolId, Boolean current) throws ExceptionEducationNotFound {
        Education updatedEducation = findEducationById(educationId);
        return educationRepository.updateById(updatedEducation, new Education(studentId, schoolId, current));
    }


    public Education deleteEducationById(Long educationId) throws ExceptionEducationNotFound {
        Education education = findEducationById(educationId);
        return educationRepository.deleteById(education);
    }

    public Education deleteEducationByStudentId(Long studentId) throws ExceptionCurrentEducationNotFound, ExceptionStudentNotFound, ExceptionEducationNotFound {
        Student student = studentService.findStudentById(studentId);
        List<Education> allEducationsForStudent = getAllEducationsForStudent(student.getStudentId());
        for(Education ed: allEducationsForStudent){
            if(ed.getCurrent()) {
                ed.setCurrent(false);
                updateEducation(ed.getEducationId(), ed.getStudentId(), ed.getSchoolId(), false);
                return ed;
            }
        }
        throw new ExceptionCurrentEducationNotFound();
    }

    public List<Education> getAllEducationsForStudent(Long studentId){
        return educationRepository.getAllEducationsForStudent(studentId);
    }
}
