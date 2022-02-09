package com.springlearn.repository;

import com.springlearn.entity.Education;
import com.springlearn.entity.School;
import com.springlearn.entity.Student;
import com.springlearn.exception.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public EducationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Education save(Education education) throws ExceptionStudentNotFound, ExceptionSchoolNotFound, ExceptionAlreadyCurrentEducation {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if(session.find(Student.class, education.getStudentId()) == null) {
            throw new ExceptionStudentNotFound(education.getStudentId());
        }
        if(session.find(School.class, education.getSchoolId()) == null) {
            throw new ExceptionSchoolNotFound(education.getSchoolId());
        }

        List<Education> allEducationsForStudent = getAllEducationsForStudent(education.getStudentId());
        for(Education ed: allEducationsForStudent){
           if(ed.getCurrent() == true) {
               throw new ExceptionAlreadyCurrentEducation(ed.getSchoolId());
           }
            session.update(ed);
        }

        session.save(education);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return education;
    }

    public Education findById(Long educationId) throws ExceptionEducationNotFound {
        Session session = sessionFactory.openSession();
        Education education = session.find(Education.class, educationId);
        if(education == null) {
            throw new ExceptionEducationNotFound(educationId);
        }
        session.close();
        return education;
    }

    public Education updateById(Long educationId, Education education) throws ExceptionEducationNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Education updatedEducation = session.find(Education.class, educationId);
        if(updatedEducation == null) {
            throw new ExceptionEducationNotFound(educationId);
        }
        updatedEducation.setStudentId(education.getStudentId());
        updatedEducation.setSchoolId(education.getSchoolId());
        updatedEducation.setCurrent(education.getCurrent());
        session.update(updatedEducation);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedEducation;
    }

    public Education deleteById(Long educationId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Education education = session.find(Education.class, educationId);
        session.delete(education);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return education;
    }

    public Education deleteByStudentId(Long studentId) throws ExceptionCurrentEducationNotFound, ExceptionEducationNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Education> allEducationsForStudent = getAllEducationsForStudent(studentId);
        for(Education ed: allEducationsForStudent){
            if(ed.getCurrent() == true) {
                ed.setCurrent(false);
                Education updatedEd = updateById(ed.getEducationId(),
                        new Education(ed.getStudentId(), ed.getSchoolId(), false));
                return ed;
            }
        }
        throw new ExceptionCurrentEducationNotFound();
    }

    public List<Education> getAllEducationsForStudent(Long studentId){
            List<Education> educations;
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            educations = session.createSQLQuery("SELECT * FROM Education as e WHERE e.studentId = " + studentId)
                    .addEntity(Education.class).list();

            return educations;
    }
}
