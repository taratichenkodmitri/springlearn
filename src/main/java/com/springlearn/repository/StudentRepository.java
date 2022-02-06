package com.springlearn.repository;

import com.springlearn.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;

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
        
        Query query = session.createSQLQuery("INSERT INTO Student (name, uin) VALUES (:name, :uin)");
        query.setParameter("name",student.getName());
        query.setParameter("uin",student.getUin());
        query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        return student;
    }
    public Student findById(Long studnetId){
        Session session = sessionFactory.openSession();
        Student student = session.find(Student.class, studnetId);
        session.close();

        return student;
    }
}
