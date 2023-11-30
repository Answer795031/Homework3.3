package ru.hogwarts.school_3.service;

import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty updateFaculty(Long id, Faculty faculty);

    Faculty removeFaculty(Long id);

    List<Faculty> facultyByColor(String color);

    public Faculty findByName(String name);

    public Faculty findByColor(String color);

    public Collection<Student> getStudents(Faculty faculty);
}
