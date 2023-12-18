package ru.hogwarts.school_3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school_3.model.Student;

import java.net.URI;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }


    /**
     * Для теста выбрано сравнение isNotNull, так как генерация id происходит на уровне БД, соответственно,
     * id студента, которого возвращает БД заранее неизвестен и сравнение не проходит.
     */
    @Test
    public void testAddStudent() throws Exception {

        // student - студент, добавляемый в БД
        Student student = new Student("Max", 26);

        // проверяем что после добавления объекта студента в БД, объект не равен null
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:"
                        + port
                        + "/student/post/{name}/{age}",
                        student,
                        Student.class,
                        student.getName(),
                        student.getAge()))
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {

        // student - студент, добавляемый в БД
        Student student = new Student("Max", 26);

        // обновляем объект student чтобы можно было корректно вызвать getId()
        student = studentController.addStudent(student.getName(), student.getAge());
        Long id = student.getId();

        // сравниваем студента в БД с объектом student
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/student/{id}/get",
                        Student.class,
                        id))
                .isEqualTo(student);
    }

    @Test
    public void testUpdateStudent() throws Exception {

        // student - изначальный студент, добавляемый в БД
        // updateStudent - данные стуента после изменения
        Student student = new Student("Max", 26);
        Student updateStudent = new Student("Pavel", 27);

        // добавляем студента в БД
        student = studentController.addStudent(student.getName(), student.getAge());

        // присваиваем id студента объекту updateStudent для корректного сравнения результатов после PUT
        Long id = student.getId();
        updateStudent.setId(id);

        // изменяем запись студента в БД
        this.restTemplate.put("http://localhost:"
                + port
                + "/student/{id}/update/{name}/{age}",
                updateStudent,
                id,
                updateStudent.getName(),
                updateStudent.getAge());

        // получаем обновленную запись студента из БД и сравниваем с объектом updateStudent
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/student/{id}/get",
                        Student.class,
                        id))
                .isEqualTo(updateStudent);
    }

    @Test
    public void testRemoveStudent() throws Exception {

        // student - студент, добавляемый в БД
        // emptyStudent - запись с пустыми полями равными null - для сравнения с БД после удаления записи
        Student student = new Student("Max", 26);
        Student emptyStudent = new Student();

        // обновляем объект student чтобы можно было корректно вызвать getId()
        student = studentController.addStudent(student.getName(), student.getAge());
        Long id = student.getId();

        // удаляем запись студента в БД
        this.restTemplate.delete("http://localhost:"
                + port
                + "/student/{id}/remove",
                id);

        // проверяем что запись удалена
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/student/{id}/get",
                        Student.class,
                        id))
                .isEqualTo(emptyStudent);
    }
}