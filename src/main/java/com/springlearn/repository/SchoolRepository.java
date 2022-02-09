package com.springlearn.repository;

import com.springlearn.entity.School;
import com.springlearn.entity.Student;
import com.springlearn.entity.Teacher;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.exception.ExceptionTeacherNotFound;
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

    public School findById(Long schoolId) throws ExceptionSchoolNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        School school = session.find(School.class, schoolId);
        if(school == null) {
            throw new ExceptionSchoolNotFound(schoolId);
        }
        session.close();

        return school;
    }

    public School updateById(Long schoolId, School school) throws ExceptionSchoolNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        School updatedSchool = session.find(School.class, schoolId);
        if(updatedSchool == null) {
            throw new ExceptionSchoolNotFound(schoolId);
        }
        updatedSchool.setTitle(school.getTitle());
        updatedSchool.setType(school.getType());
        session.update(updatedSchool);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedSchool;
    }

    public School deleteById(Long schoolId) throws ExceptionSchoolNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        School school = session.find(School.class, schoolId);
        if(school == null) {
            throw new ExceptionSchoolNotFound(schoolId);
        }
        session.delete(school);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return school;
    }
}
