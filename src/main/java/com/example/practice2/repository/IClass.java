package com.example.practice2.repository;

import com.example.practice2.model.ClassRoom;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IClass extends JpaRepository<ClassRoom,Integer> , JpaSpecificationExecutor<ClassRoom> {
}
