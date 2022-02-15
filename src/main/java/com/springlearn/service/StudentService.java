package com.springlearn.service;

import com.springlearn.entity.Student;
import com.springlearn.exception.ExceptionStudentNotFound;
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

    public Long saveStudent(String name, Long uin, String questionably) {
        return studentRepository.save(new Student(name, uin, questionably)).getStudentId();
    }

    public Student findStudentById(Long studentId) throws ExceptionStudentNotFound {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new ExceptionStudentNotFound(studentId);
        }
        return student;
    }

    public Student updateStudent(Long studentId, String name, Long uin, String questionably) throws ExceptionStudentNotFound {
        Student updatedStudent = findStudentById(studentId);
        return studentRepository.updateById(updatedStudent, new Student(name, uin, questionably));
    }

    public Student deleteStudentById(Long studentId) throws ExceptionStudentNotFound {
        Student student = findStudentById(studentId);
        return studentRepository.deleteById(student);
    }

    public List<Student> getAllStudentPaginated(Long number) {
        Long sizePage = Long.valueOf(3);
        number = number == 1 ? 0 : (number - 1) * sizePage;
        return studentRepository.getAllStudents(number, sizePage);
    }
}
