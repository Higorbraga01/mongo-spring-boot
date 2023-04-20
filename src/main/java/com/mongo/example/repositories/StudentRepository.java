package com.mongo.example.repositories;

import com.mongo.example.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEmail(String email);
}
