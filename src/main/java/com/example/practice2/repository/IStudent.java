package com.example.practice2.repository;

import com.example.practice2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {

}
