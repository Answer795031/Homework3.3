package ru.hogwarts.school_3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;
import ru.hogwarts.school_3.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/get")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping("/post/{name}/{color}")
    public Faculty addFaculty(@PathVariable String name,
                              @PathVariable String color) {
        return facultyService.addFaculty(name, color);
    }

    @PutMapping("/{id}/update/{name}/{color}")
    public Faculty updateFaculty(@PathVariable Long id,
                                 @PathVariable String name,
                                 @PathVariable String color) {
        return facultyService.updateFaculty(id, name, color);
    }

    @DeleteMapping("/{id}/remove")
    public Faculty removeFaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }

    @GetMapping("/color/{color}")
    public Collection<Faculty> facultyByColor(@PathVariable String color) {
        return facultyService.facultyByColor(color);
    }

    @GetMapping("/name-or-color")
    public Faculty findByNameOrColor(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return facultyService.findByName(name);
        }
        if (color != null && !color.isBlank()) {
            return facultyService.findByColor(color);
        }
        return null;
    }

    @GetMapping("/student/{id}")
    public Collection<Student> getStudents(@PathVariable Long id) {
        return facultyService.getStudents(id);
    }
}
