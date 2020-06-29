package com.rios.demotdd.service;

import com.rios.demotdd.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void create() {
        Student student = createStudent();
        Student savedStudent = studentService.create(student);
        Assertions.assertEquals(student, savedStudent);
        Assertions.assertNotNull(savedStudent.getRegistrationCode());
    }

    @Test
    void update() {
        Student savedStudent = studentService.create(createStudent());
        Student toUpdate = studentService.findByRegistrationCode(savedStudent.getRegistrationCode()).get();
        toUpdate.setFirstName("Updated");
        toUpdate = studentService.update(toUpdate);
        Assertions.assertEquals(savedStudent.getRegistrationCode(), toUpdate.getRegistrationCode());
        Assertions.assertNotEquals(savedStudent, toUpdate);
    }

    @Test
    void findByRegistrationCode() {
        Student student = studentService.create(createStudent());
        Optional<Student> optionalStudent = studentService.findByRegistrationCode(student.getRegistrationCode());
        Assertions.assertTrue(optionalStudent.isPresent());
    }

    private Student createStudent() {
        return Student.builder()
                .firstName("First")
                .lastName("Last")
                .email("Email")
                .birthday(LocalDate.now())
                .registrationDate(LocalDate.now())
                .build();
    }

    @Test
    void findAll() {
        int total = 3;
        for(int i = 0; i < total; i++) {
            studentService.create(createStudent());
        }
        List<Student> all = studentService.findAll();
        Assertions.assertEquals(total, all.size());
    }

}
