package com.example.practice2.controller.api;

import com.example.practice2.model.Student;
import com.example.practice2.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentApi {
    @Autowired
    IStudentService studentService;
    @GetMapping
    public ResponseEntity<Page<Student>> show(@PageableDefault(2)Pageable pageable){
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for(FieldError err: fieldErrorList){
                stringBuilder.append(err.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(stringBuilder.toString(),HttpStatus.BAD_REQUEST);
        }
        studentService.save(student);
        return new ResponseEntity<>("Created",HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Student student,@PathVariable("id") int id){
        studentService.update(id,student);
        return new ResponseEntity<>("Updated",HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        studentService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Student>> search(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) Integer code,
            @RequestParam(value = "idClass",required = false) Integer idClass,
            @RequestParam(value = "sort",required = false) String sort,
            @PageableDefault(2) Pageable pageable
    ){
       Page<Student> students =  studentService.search(sort,name,code,idClass, pageable);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }


}
