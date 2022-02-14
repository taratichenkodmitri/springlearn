package com.springlearn.repository;

import com.springlearn.entity.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public SchoolRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public School save(School school) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(school);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return school;
    }

    public School findById(Long schoolId) {
        Session session = sessionFactory.openSession();
        School school = session.find(School.class, schoolId);
        session.close();

        return school;
    }

    public School updateById(School updatedSchool, School school) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        updatedSchool.setTitle(school.getTitle());
        updatedSchool.setType(school.getType());
        session.update(updatedSchool);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedSchool;
    }

    public School deleteById(School school) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(school);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return school;
    }
}
