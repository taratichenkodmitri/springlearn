package com.springlearn.controller;

import com.springlearn.controller.dto.EducationRequestDto;
import com.springlearn.controller.dto.EducationResponseDto;
import com.springlearn.exception.*;
import com.springlearn.exception.response.ResponseException;
import com.springlearn.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EducationController {
    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public Long createEducation(@RequestBody EducationRequestDto educationRequestDto)
            throws ExceptionStudentNotFound,
            ExceptionSchoolNotFound,
            ExceptionAlreadyCurrentEducation {
        return educationService.saveEducation(educationRequestDto.getStudentId(),
                educationRequestDto.getSchoolId(),
                educationRequestDto.getCurrent());
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.GET)
    public EducationResponseDto getEducation(@PathVariable Long educationId) throws ExceptionEducationNotFound {
        return new EducationResponseDto(educationService.findEducationById(educationId));
    }

    @RequestMapping(value = "/educationForStudents/{studentId}", method = RequestMethod.DELETE)
    public EducationResponseDto deleteEducationByStudentId(@PathVariable Long studentId) throws ExceptionCurrentEducationNotFound, ExceptionEducationNotFound {
        return new EducationResponseDto(educationService.deleteEducationByStudentId(studentId));
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.DELETE)
    public EducationResponseDto deleteEducation(@PathVariable Long educationId) {
        return new EducationResponseDto(educationService.deleteEducationById(educationId));
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.PATCH)
    public EducationResponseDto updateEducation(@PathVariable Long educationId,
                                     @RequestBody EducationRequestDto educationRequestDto) throws ExceptionEducationNotFound {
        return new EducationResponseDto(educationService.updateSchool(educationId,
                educationRequestDto.getStudentId(),
                educationRequestDto.getSchoolId(),
                educationRequestDto.getCurrent()));
    }

    @RequestMapping(value = "/educationForStudents/{studentId}", method = RequestMethod.GET)
    public List<EducationResponseDto> getAllEducationsForStudent(@PathVariable Long studentId){
        return educationService.getAllEducationsForStudent(studentId)
                .stream()
                .map(EducationResponseDto::new).collect(Collectors.toList());
    }

    @ExceptionHandler({ExceptionStudentNotFound.class,
            ExceptionSchoolNotFound.class,
            ExceptionAlreadyCurrentEducation.class,
            ExceptionCurrentEducationNotFound.class,
            ExceptionEducationNotFound.class})
    public ResponseException handleException(Exceptions e) {
        return new ResponseException(e.getMessage());
    }
}
