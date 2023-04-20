package com.mongo.example.controllers;

import com.mongo.example.models.Student;
import com.mongo.example.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {


    final StudentService service;

    @GetMapping
    public List<Student> fetchStudents() {
        return service.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@RequestBody Student student, @PathVariable String studentId) {
        return service.updateStudant(student, studentId);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable String studentId) {
        service.deleteStudent(studentId);
    }
}
