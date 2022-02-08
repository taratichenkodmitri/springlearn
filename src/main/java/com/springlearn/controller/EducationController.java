package com.springlearn.controller;

import com.springlearn.controller.dto.EducationRequestDto;
import com.springlearn.controller.dto.EducationResponseDto;
import com.springlearn.exception.EducationException;
import com.springlearn.exception.EducationExceptionCurrent;
import com.springlearn.exception.EducationExceptionSchoolNotFound;
import com.springlearn.exception.EducationExceptionStudentNotFound;
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
            throws EducationExceptionStudentNotFound,
            EducationExceptionSchoolNotFound,
            EducationExceptionCurrent {
        return educationService.saveEducation(educationRequestDto.getStudentId(),
                educationRequestDto.getSchoolId(),
                educationRequestDto.getCurrent());
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.GET)
    public EducationResponseDto getEducation(@PathVariable Long educationId) {
        return new EducationResponseDto(educationService.findEducationById(educationId));
    }

    @RequestMapping(value = "/educationForStudents/{studentId}", method = RequestMethod.DELETE)
    public EducationResponseDto deleteEducationByStudentId(@PathVariable Long studentId) throws EducationExceptionCurrent {
        return new EducationResponseDto(educationService.deleteEducationByStudentId(studentId));
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.DELETE)
    public EducationResponseDto deleteEducation(@PathVariable Long educationId) {
        return new EducationResponseDto(educationService.deleteEducationById(educationId));
    }

    @RequestMapping(value = "/education/{educationId}", method = RequestMethod.PATCH)
    public EducationResponseDto updateEducation(@PathVariable Long educationId,
                                     @RequestBody EducationRequestDto educationRequestDto) {
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

    @ExceptionHandler({EducationExceptionStudentNotFound.class,
            EducationExceptionSchoolNotFound.class,
            EducationExceptionCurrent.class})
    public ResponseException handleException(EducationException e) {
        return new ResponseException(e.getMessage());
    }
}
