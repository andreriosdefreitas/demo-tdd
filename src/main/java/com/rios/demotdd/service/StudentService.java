package com.rios.demotdd.service;

import com.rios.demotdd.repository.StudentRepository;
import com.rios.demotdd.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(final Student student) {
        if(student.getRegistrationCode() != null) {
            throw new IllegalArgumentException("RegistrationCode should be null");
        }
        return studentRepository.save(student);
    }

    public Student update(final Student student) {
        if(student.getRegistrationCode() == null) {
            throw new IllegalArgumentException("RegistrationCode shouldn't be null");
        }
        return studentRepository.save(student);
    }

    public Optional<Student> findByRegistrationCode(Long registrationCode) {
        return studentRepository.findById(registrationCode);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
