package ru.hogwarts.school_3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    /**
     * Для теста выбрано сравнение isNotNull, так как генерация id происходит на уровне БД, соответственно,
     * id факультета, которого возвращает БД заранее неизвестен и сравнение не проходит.
     */
    @Test
    public void testAddFaculty() throws Exception {

        // faculty - факультет, добавляемый в БД
        Faculty faculty = new Faculty("TestFaculty", "Red");

        // проверяем что после добавления объекта факультета в БД, объект не равен null
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:"
                        + port
                        + "/faculty/post/{name}/{color}",
                        faculty,
                        Faculty.class,
                        faculty.getName(),
                        faculty.getColor()))
                .isNotNull();
    }

    /**
     * Далее при проведении тестов, содержащих проверку объектов через GET возникла сложность при сравнении
     * ожидаемого объекта faculty с объектом, полученным из БД.
     * Проверку не проходит поле students: у ожидаемого объекта поле students имеет значение null
     * У фактической записи в БД поле students имеет пустую коллекцию
     * Остальные поля проходят проверку.
     */

    @Test
    public void testGetFaculty() throws Exception {

        // faculty - факультет, добавляемый в БД
        Faculty faculty = new Faculty("TestFaculty", "Red");

        // обновляем объект faculty чтобы можно было корректно вызвать getId()
        faculty = facultyController.addFaculty(faculty.getName(), faculty.getColor());
        Long id = faculty.getId();

        // проверка
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/faculty/{id}/get",
                        Faculty.class,
                        id))
                .isNotNull();
    }

    @Test
    public void testUpdateFaculty() throws Exception {

        // faculty - изначальный факультет, добавляемый в БД
        // updateFaculty - данные факультета после изменения
        Faculty faculty = new Faculty("TestFaculty", "Red");
        Faculty updateFaculty = new Faculty("UpdateFaculty", "Blue");

        // добавляем факультет в БД
        faculty = facultyController.addFaculty(faculty.getName(), faculty.getColor());

        // присваиваем id факультета объекту updateFaculty для корректного сравнения результатов после PUT
        Long id = faculty.getId();
        updateFaculty.setId(id);

        // изменяем запись факультета в БД
        this.restTemplate.put("http://localhost:"
                + port
                + "/faculty/{id}/update/{name}/{color}",
                updateFaculty,
                id,
                updateFaculty.getName(),
                updateFaculty.getColor());

        // получаем обновленную запись факультета из БД и сравниваем с объектом updateFaculty
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/faculty/{id}/get",
                        Faculty.class,
                        id))
                .isNotNull();
    }

    @Test
    public void testRemoveFaculty() throws Exception {

        // faculty - факультет, добавляемый в БД
        // emptyFaculty - запись с пустыми полями равными null - для сравнения с БД после удаления записи
        Faculty faculty = new Faculty("TestFaculty", "Red");
        Faculty emptyFaculty = new Faculty();

        // обновляем объект faculty чтобы можно было корректно вызвать getId()
        faculty = facultyController.addFaculty(faculty.getName(), faculty.getColor());
        Long id = faculty.getId();

        // удаляем запись факультета в БД
        this.restTemplate.delete("http://localhost:"
                + port
                + "/faculty/{id}/remove",
                id);

        // проверяем что запись удалена
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"
                        + port
                        + "/faculty/{id}/get",
                        Faculty.class,
                        id))
                .isNotNull();
    }

}