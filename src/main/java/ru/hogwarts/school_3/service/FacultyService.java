package ru.hogwarts.school_3.service;

import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;
import ru.hogwarts.school_3.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty addFaculty(String name, String color);

    Faculty getFaculty(Long id);

    Faculty updateFaculty(Long id, String name, String color);

    Faculty removeFaculty(Long id);

    List<Faculty> facultyByColor(String color);

    public Faculty findByName(String name);

    public Faculty findByColor(String color);

    public Collection<Student> getStudents(Long id);
}
