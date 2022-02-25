package com.springlearn.service;

import com.springlearn.entity.Role;
import com.springlearn.entity.Student;
import com.springlearn.exception.ExceptionStudentNotFound;
import com.springlearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PermissionService permissionService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          PermissionService permissionService) {
        this.studentRepository = studentRepository;
        this.permissionService = permissionService;
    }

    public Long saveStudent(String name, Long uin, String questionably, Authentication authentication) {
        Student student = new Student(name, uin, questionably);
        studentRepository.save(student).getId();
        permissionService.addPermissionForUser(student, BasePermission.READ, authentication.getName());
        return student.getId();
    }

    @PostAuthorize("hasPermission(returnObject, 'read')")
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

    @PostFilter("hasPermission(filterObject, 'read')")
    public List<Student> getAllStudentPaginated(Long number) {
        Long sizePage = Long.valueOf(3);
        number = number == 1 ? 0 : (number - 1) * sizePage;
        return studentRepository.getAllStudents(number, sizePage);
    }
}
