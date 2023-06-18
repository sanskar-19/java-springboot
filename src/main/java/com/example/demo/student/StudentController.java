package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService StudentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.StudentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return StudentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        StudentService.addNewStudent(student);
    }

    @DeleteMapping(path="/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        System.out.println(studentId);
        StudentService.deleteStudent(studentId);
    }

    @PutMapping(path="/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        StudentService.updateStudent(name, email, studentId);
    }

}
