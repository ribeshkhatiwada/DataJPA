package com.example.demo.Controller;

import com.example.demo.Model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> student = Arrays.asList(
            new Student(1,"James Bond"),
            new Student(2, "Maira Jones")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
            return student.stream()
                          .filter(student -> studentId.equals(student.getStudentId()))
                          .findFirst()
                          .orElseThrow(() -> new IllegalArgumentException("Student "+studentId+ " doesn't exist"));
    }

    @GetMapping()
    public List<Student> getStuent(){
        return student;
    }
}

