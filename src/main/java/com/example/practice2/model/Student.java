package com.example.practice2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Date;


@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "name not be null")
    private String name;
    private Date date;
    private int code;
    @ManyToOne
    public ClassRoom aClass;
}
