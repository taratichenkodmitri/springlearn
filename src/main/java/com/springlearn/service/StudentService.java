package com.springlearn.service;

import com.springlearn.entity.Student;
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

    public Long saveStudent(String name, Long uin) {
        return studentRepository.save(new Student(name, uin)).getStudentId();
    }

    public Student findStudentById(Long accountId){
        return studentRepository.findById(accountId);
    }
}
