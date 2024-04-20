package com.example.practice2.service;

import com.example.practice2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);

    void save(Student student);

    void delete(int id);

    void update(int id, Student student);

    Optional<Student> findById(int id);


    Page<Student> search(String sort, String name, Integer code, Integer className, Pageable pageable);
}
