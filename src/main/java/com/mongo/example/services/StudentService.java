package com.mongo.example.services;

import com.mongo.example.models.Student;
import com.mongo.example.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    final StudentRepository repository;

   public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student createStudent(Student student) {
       return repository.save(student);
    }

    public Student updateStudant(Student student, String studentId) {
        repository.findById(studentId).ifPresentOrElse(s -> {
            student.setId(s.getId());
            repository.save(student);
            System.out.println(student.getFirstName() + " updated successfully");
        }, () -> {
            throw new IllegalArgumentException("student not found");
        });
        return student;
    }

    public void deleteStudent(String studentId) {
        repository.findById(studentId).ifPresentOrElse(student -> {
            repository.deleteById(studentId);
            System.out.println(student.getFirstName() + " deleted successfully");
        }, () -> {
            throw new IllegalArgumentException("student not found");
        });
    }
}
