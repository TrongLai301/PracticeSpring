package com.example.practice2.service.impl;

import com.example.practice2.dto.StudentSpec;
import com.example.practice2.model.Student;
import com.example.practice2.repository.IStudent;
import com.example.practice2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudent iStudent;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return iStudent.findAll(pageable);
    }

    @Override
    public void save(Student student) {
        iStudent.save(student);
    }

    @Override

    public void delete(int id) {
        iStudent.deleteById(id);
    }

    @Override
    public void update(int id, Student student) {
        student.setId(id);
        iStudent.save(student);
    }

    @Override
    public Optional<Student> findById(int id) {
        return iStudent.findById(id);
    }

    @Override
    public Page<Student> search(String sort, String name, Integer code, Integer className, Pageable pageable) {
        switch (sort) {
            case "nameACS":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
                break;

            case "nameDes":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").descending());
                break;

            case "codeACS":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("code").ascending());
                break;

            case "codeDes":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("code").descending());
                break;

            default:
                break;
        }
        Specification<Student> specification = Specification.where(StudentSpec.hasClassRoom(className).and(StudentSpec.hasCode(code)).and(StudentSpec.hasName(name)));
        return iStudent.findAll(specification, pageable);
    }
}
