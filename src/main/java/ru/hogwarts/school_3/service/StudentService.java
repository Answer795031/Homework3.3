package ru.hogwarts.school_3.service;

import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(String name, Integer age);

    Student getStudent(Long id);

    Student updateStudent(Long id, String name, Integer age);

    Student removeStudent(Long id);

    List<Student> studentsByAge(Integer age);

    public List<Student> findAllByAgeBetween(Integer min, Integer max);

    public Faculty getFaculty(Long id);
}
