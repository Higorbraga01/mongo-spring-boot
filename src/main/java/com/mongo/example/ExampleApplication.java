package com.mongo.example;

import com.mongo.example.models.Address;
import com.mongo.example.models.Gender;
import com.mongo.example.models.Student;
import com.mongo.example.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
  //github.com/amigoscode

    @Bean
    CommandLineRunner runner(
            StudentRepository repository, MongoTemplate mongoTemplate) {
        return  args -> {
            Address address = new Address(
                    "Brasil",
                    "Queimados",
                    "26383250"
            );
            String email = "higorbraga@email.com";
            Student student = new Student(
                    "Higor",
                    "Braga",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Java for Dumies", "Death-Note"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

//            usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println(s + " already exists");
                    }, ()-> {
                        System.out.println("Inserting student "+ student);
                        repository.save(student);
                    });
        };
    }

    private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").in(email));
        List<Student> students = mongoTemplate.find(query, Student.class);

        if(students.size() > 1) {
            throw new IllegalStateException(
                    "found many students with email " + email);
        }
        if(students.isEmpty()) {
            System.out.println("Inserting student "+ student);
            repository.save(student);
        } else {
            System.out.println(student + " already exists");
        }
    }
}
