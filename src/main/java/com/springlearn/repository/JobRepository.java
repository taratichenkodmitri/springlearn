package com.springlearn.repository;

import com.springlearn.entity.*;
import com.springlearn.exception.ExceptionJobNotFound;
import com.springlearn.exception.ExceptionSchoolNotFound;
import com.springlearn.exception.ExceptionTeacherNotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public JobRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Job save(Job job) throws ExceptionTeacherNotFound, ExceptionSchoolNotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(job);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return job;
    }

    public Job findById(Long jobId) {
        Session session = sessionFactory.openSession();
        Job job = session.find(Job.class, jobId);
        session.close();
        return job;
    }

    public Job updateById(Job updatedJob, Job job) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        updatedJob.setTeacherId(job.getTeacherId());
        updatedJob.setSchoolId(job.getSchoolId());
        session.update(updatedJob);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return updatedJob;
    }

    public Job deleteById(Job job) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(job);
        session.flush();
        session.getTransaction().commit();
        session.close();

        return job;
    }

    public List<Job> getAllJobsForTeacher(Long teacherId){
        List<Job> jobs;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        jobs = session.createSQLQuery("SELECT * FROM Job as j WHERE j.teacherId = " + teacherId)
                .addEntity(Job.class).list();

        return jobs;
    }

}
