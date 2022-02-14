package com.springlearn.repository;

import com.springlearn.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TeacherRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Teacher save(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(teacher);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return teacher;
    }

    public Teacher findById(Long teacherId) {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.find(Teacher.class, teacherId);
        session.close();

        return teacher;
    }

    public Teacher updateById(Teacher updatedTeacher, Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        updatedTeacher.setName(teacher.getName());
        updatedTeacher.setUin(teacher.getUin());
        session.update(updatedTeacher);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedTeacher;
    }

    public Teacher deleteById(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(teacher);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return teacher;
    }
}
