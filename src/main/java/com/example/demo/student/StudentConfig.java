package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        Student sanskar = new Student(
                "Sanskar",
                22,
                "sanskar@my3dmeta.com",
                LocalDate.of(2002, Month.FEBRUARY,3));

        Student sarthak = new Student(
                "Sarthak",
                24,
                "sarthak@my3dmeta.com",
                LocalDate.of(1999, Month.MAY,13));

        repository.saveAll(List.of(sanskar, sarthak));
        return args -> {
        };
    }
}
