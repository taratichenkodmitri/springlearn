package com.springlearn.repository;

import com.springlearn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

}
