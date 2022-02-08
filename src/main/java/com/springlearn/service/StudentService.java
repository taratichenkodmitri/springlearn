package com.springlearn.service;

import com.springlearn.entity.Education;
import com.springlearn.entity.Student;
import com.springlearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long saveStudent(String name, Long uin) {
        return studentRepository.save(new Student(name, uin)).getStudentId();
    }

    public Student findStudentById(Long studentId){
        return studentRepository.findById(studentId);
    }

    public Student updateStudent(Long studentId, String name, Long uin) {
        return studentRepository.updateById(studentId, new Student(name, uin));
    }

    public Student deleteStudentById(Long studentId) {
        return studentRepository.deleteById(studentId);
    }

}
