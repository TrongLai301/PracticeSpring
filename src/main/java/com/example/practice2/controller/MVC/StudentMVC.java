package com.example.practice2.controller.MVC;

import com.example.practice2.model.Student;
import com.example.practice2.repository.IClass;
import com.example.practice2.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("student")
public class StudentMVC {
    @Autowired
    IStudentService studentService;
    @Autowired
    IClass iClass;
    @GetMapping
    public ModelAndView show(@PageableDefault(2) Pageable pageable
    ){
        Page<Student> students = studentService.findAll(pageable);
       ModelAndView modelAndView = new ModelAndView("/views/list");
       modelAndView.addObject("students",students);
       return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView  create(){
        ModelAndView modelAndView = new ModelAndView("/views/create");
        modelAndView.addObject("students", new Student());
        modelAndView.addObject("clasS", iClass.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(@Valid @ModelAttribute Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for(FieldError err: fieldErrorList){
                stringBuilder.append(err.getDefaultMessage()).append("\n");
            }
        }
        studentService.save(student);
        return new ModelAndView("redirect:/student");
    }
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") int id){
        Student student = studentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/views/update");
        modelAndView.addObject("clasS", iClass.findAll());
        modelAndView.addObject("student",student);
        return modelAndView;
    }
    @PostMapping("/saveUpdate")
    public ModelAndView saveUpdate(@ModelAttribute Student student,@RequestParam("id") int id){
        studentService.update(id,student);
        return new ModelAndView("redirect:/student");

    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") int id){
        studentService.delete(id);
        return new ModelAndView("redirect:/student");
    }
    @PostMapping("/search")
    public ModelAndView search(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) Integer code,
            @RequestParam(value = "idClass",required = false) Integer idClass,
            @RequestParam(value = "sort",required = false) String sort,
            @PageableDefault(2) Pageable pageable
    ){
        if( name == null && code == null && idClass == null && sort == null){
            return new ModelAndView("redirect:/student");
        }
        ModelAndView modelAndView = new ModelAndView("/views/list");
        Page<Student> students =  studentService.search(sort,name,code,idClass, pageable);
        modelAndView.addObject("students",students);
        return modelAndView;
    }

}
