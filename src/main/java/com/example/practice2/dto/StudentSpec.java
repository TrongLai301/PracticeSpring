package com.example.practice2.dto;

import com.example.practice2.model.Student;
import com.example.practice2.repository.IClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class StudentSpec {
    public static Specification<Student> hasName(String name){
        return (root, query, criteriaBuilder) -> {
            if(name == null){
                return null;
            }
            return criteriaBuilder.like(root.get("name"),"%"+name+"%");
        };
    }
    public static Specification<Student> hasCode(Integer code){
        return (root, query, criteriaBuilder) -> {
            if(code == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("code"),code);
        };
    }
    public static Specification<Student> hasClassRoom(Integer idClassRoom){
        return (root, query, criteriaBuilder) -> {
            if(idClassRoom == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("aClass").get("id"),idClassRoom);
        };
    }

}
