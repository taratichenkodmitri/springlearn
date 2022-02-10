package com.springlearn.service;

import com.springlearn.entity.Student;
import com.springlearn.exception.ExceptionStudentNotFound;
import com.springlearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long saveStudent(String name, Long uin, String questionably) {
        return studentRepository.save(new Student(name, uin, questionably)).getStudentId();
    }

    public Student findStudentById(Long studentId) throws ExceptionStudentNotFound {
        return studentRepository.findById(studentId);
    }

    public Student updateStudent(Long studentId, String name, Long uin, String questionably) throws ExceptionStudentNotFound {
        return studentRepository.updateById(studentId, new Student(name, uin, questionably));
    }

    public Student deleteStudentById(Long studentId) throws ExceptionStudentNotFound {
        return studentRepository.deleteById(studentId);
    }

}
