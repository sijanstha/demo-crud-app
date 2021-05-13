package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Student;

//higher level :: sql query records hamro model/entity link up
// base query sql query
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}

// mongodb