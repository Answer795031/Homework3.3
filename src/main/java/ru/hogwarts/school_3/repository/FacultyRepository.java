package ru.hogwarts.school_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school_3.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Faculty findByNameIgnoreCase(String name);

    public Faculty findByColorIgnoreCase(String color);
}
