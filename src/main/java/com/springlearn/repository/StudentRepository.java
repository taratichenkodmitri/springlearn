package com.springlearn.repository;

import com.springlearn.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Student save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public Student findById(Long studentId){
        Session session = sessionFactory.openSession();
        Student student = session.find(Student.class, studentId);
        session.close();

        return student;
    }
    
    public Student updateById(Long studentId, Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student updatedStudent = session.find(Student.class, studentId);
        updatedStudent.setName(student.getName());
        updatedStudent.setUin(student.getUin());
        session.update(updatedStudent);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedStudent;
    }

    public Student deleteById(Long studentId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.find(Student.class, studentId);
        session.delete(student);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return student;
    }

}
