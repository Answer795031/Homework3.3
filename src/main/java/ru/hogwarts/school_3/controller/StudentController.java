package ru.hogwarts.school_3.controller;

import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}/get")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/post/{name}/{age}", method = RequestMethod.POST)
    public Student addStudent(@PathVariable String name,
                              @PathVariable Integer age) {
        return studentService.addStudent(name, age);
    }

    @PutMapping("/{id}/update/{name}/{age}")
    public Student updateStudent(@PathVariable Long id,
                                 @PathVariable String name,
                                 @PathVariable Integer age) {
        return studentService.updateStudent(id, name, age);
    }

    @DeleteMapping("/{id}/remove")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @GetMapping("/by-age/{age}")
    public Collection<Student> studentsByAge(@PathVariable Integer age) {
        return studentService.studentsByAge(age);
    }

    @GetMapping("/age-between")
    public List<Student> findAllByAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.findAllByAgeBetween(min, max);
    }

    @GetMapping("/faculty/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFaculty(id);
    }
}
