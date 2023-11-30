package ru.hogwarts.school_3.service;

import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Long id, Student student);

    Student removeStudent(Long id);

    List<Student> studentsByAge(int age);

    public List<Student> findAllByAgeBetween(Integer min, Integer max);

    public Faculty getFaculty(Student student);
}
