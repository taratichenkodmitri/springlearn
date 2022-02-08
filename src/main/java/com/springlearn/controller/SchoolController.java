package com.springlearn.controller;

import com.springlearn.controller.dto.SchoolRequestDto;
import com.springlearn.controller.dto.SchoolResponseDto;
import com.springlearn.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public Long createSchool(@RequestBody SchoolRequestDto schoolRequestDto) {
        return schoolService.saveSchool(schoolRequestDto.getTitle(), schoolRequestDto.getType());
    }

    @RequestMapping(value = "school/{schoolId}", method = RequestMethod.GET)
    public SchoolResponseDto getSchool(@PathVariable Long schoolId) {
        return  new SchoolResponseDto(schoolService.findSchoolById(schoolId));
    }

    @RequestMapping(value = "/school/{schoolId}", method = RequestMethod.DELETE)
    public SchoolResponseDto deleteSchool(@PathVariable Long schoolId) {
        return new SchoolResponseDto(schoolService.deleteSchoolByid(schoolId));
    }

    @RequestMapping(value = "school/{schoolId}", method = RequestMethod.PATCH)
    public SchoolResponseDto updateSchool(@RequestBody SchoolRequestDto schoolRequestDto,
                                          @PathVariable Long schoolId) {
        return new SchoolResponseDto(schoolService
                .updateSchool(schoolId, schoolRequestDto.getTitle(), schoolRequestDto.getType()));
    }
}
