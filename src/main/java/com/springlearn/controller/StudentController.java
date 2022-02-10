package com.springlearn.controller;

import com.springlearn.controller.dto.StudentRequestDto;
import com.springlearn.controller.dto.StudentResponseDto;
import com.springlearn.exception.ExceptionStudentNotFound;
import com.springlearn.exception.Exceptions;
import com.springlearn.exception.response.ResponseException;
import com.springlearn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Long createStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.saveStudent(studentRequestDto.getName(),
                studentRequestDto.getUin(),
                studentRequestDto.getQuestionablyToString());
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
    public StudentResponseDto getStudent(@PathVariable Long studentId) throws ExceptionStudentNotFound, IOException {
        return new StudentResponseDto(studentService.findStudentById(studentId));
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
    public StudentResponseDto deleteStudent(@PathVariable Long studentId) throws ExceptionStudentNotFound, IOException {
        return new StudentResponseDto(studentService.deleteStudentById(studentId));
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PATCH)
    public StudentResponseDto updateStudent(@RequestBody StudentRequestDto studentRequestDto,
                                            @PathVariable Long studentId ) throws ExceptionStudentNotFound, IOException {
        return new StudentResponseDto(studentService.updateStudent(studentId, studentRequestDto.getName(),
                                studentRequestDto.getUin(),
                                studentRequestDto.getQuestionablyToString()));
    }

    @ExceptionHandler({ExceptionStudentNotFound.class})
    public ResponseException handleException(Exceptions e) {
        return new ResponseException(e.getMessage());
    }
}
