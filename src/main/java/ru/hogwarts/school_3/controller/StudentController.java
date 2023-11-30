package ru.hogwarts.school_3.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;
import ru.hogwarts.school_3.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @GetMapping(params = "age")
    public Collection<Student> studentsByAge(@RequestParam Integer age) {
        return studentService.studentsByAge(age);
    }

    @GetMapping("/age-between")
    public List<Student> findAllByAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.findAllByAgeBetween(min, max);
    }

    @GetMapping("/faculty")
    public Faculty getFaculty(@RequestBody Student student) {
        return studentService.getFaculty(student);
    }
}
