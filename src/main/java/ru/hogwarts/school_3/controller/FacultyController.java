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

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id,
                                 @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty removeFaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }

    @GetMapping(params = "color")
    public Collection<Faculty> facultyByColor(@RequestParam String color) {
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

    @GetMapping("/student")
    public Collection<Student> getStudents(Faculty faculty) {
        return facultyService.getStudents(faculty);
    }
}
