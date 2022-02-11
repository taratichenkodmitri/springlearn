package com.springlearn.service;

import com.springlearn.entity.Teacher;
import com.springlearn.exception.ExceptionTeacherNotFound;
import com.springlearn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public Long saveTeacher(String name, Long uin) {
        return teacherRepository.save(new Teacher(name, uin)).getTeacherId();
    }

    public Teacher findTeacherById(Long teacherId) throws ExceptionTeacherNotFound {
        Teacher teacher = teacherRepository.findById(teacherId);
        if(teacher == null) {
            throw new ExceptionTeacherNotFound(teacherId);
        }
        return teacher;
    }

    public Teacher updateTeacher(Long teacherId, String name, Long uin) throws ExceptionTeacherNotFound {
        Teacher teacher = findTeacherById(teacherId);
        Teacher updatedTeacher = teacherRepository.updateById(teacher, new Teacher(name, uin));
        return updatedTeacher;
    }

    public Teacher deleteTeacherById(Long teacherId) throws ExceptionTeacherNotFound {
        Teacher teacher = findTeacherById(teacherId);
        return teacherRepository.deleteById(teacher);
    }
}
