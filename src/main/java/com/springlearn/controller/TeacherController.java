package com.springlearn.controller;

import com.springlearn.controller.dto.StudentRequestDto;
import com.springlearn.controller.dto.TeacherRequestDto;
import com.springlearn.controller.dto.TeacherResponseDto;
import com.springlearn.exception.ExceptionTeacherNotFound;
import com.springlearn.exception.Exceptions;
import com.springlearn.exception.response.ResponseException;
import com.springlearn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public Long createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        return teacherService.saveTeacher(teacherRequestDto.getName(), teacherRequestDto.getUin());
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.GET)
    public TeacherResponseDto getTeacher(@PathVariable Long teacherId) throws ExceptionTeacherNotFound {
        return new TeacherResponseDto(teacherService.findTeacherById(teacherId));
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.DELETE)
    public TeacherResponseDto deleteTeacher(@PathVariable Long teacherId) throws ExceptionTeacherNotFound {
        return new TeacherResponseDto(teacherService.deleteTeacherById(teacherId));
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.PATCH, produces = "application/xml")
    public TeacherResponseDto updateTeacher(@RequestBody StudentRequestDto studentRequestDto,
                                            @PathVariable Long teacherId ) throws ExceptionTeacherNotFound {
        return new TeacherResponseDto(teacherService.
                updateTeacher(teacherId, studentRequestDto.getName(), studentRequestDto.getUin()));
    }

    @ExceptionHandler({ExceptionTeacherNotFound.class})
    public ResponseException handleException(Exceptions e) {
        return new ResponseException(e.getMessage());
    }
}
