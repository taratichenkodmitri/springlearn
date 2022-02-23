package com.springlearn.repository;

import com.springlearn.entity.Education;
import com.springlearn.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User school) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(school);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return school;
    }

    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        List<User> users = session
                .createSQLQuery("SELECT * FROM User as u WHERE u.username = :username")
                .addEntity(User.class).setParameter("username", username).list();
        session.close();

        return users.get(0);
    }
}
