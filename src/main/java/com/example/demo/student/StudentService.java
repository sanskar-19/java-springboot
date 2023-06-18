package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentFromDB = studentRepository.findStudentByEmail(student.getEmail());

        if(studentFromDB.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id){
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("No student found");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(String name, String email, Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("No student found"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentFromDB = studentRepository.findStudentByEmail(email);
            if(studentFromDB.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }




    }
}
