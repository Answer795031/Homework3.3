package ru.hogwarts.school_3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Max;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school_3.model.Student;
import ru.hogwarts.school_3.repository.StudentRepository;
import ru.hogwarts.school_3.service.FacultyService;
import ru.hogwarts.school_3.service.StudentService;

import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addStudentTest() throws Exception {

        String name = "Max";
        int age = 26;
        Long id = 1L;

        Student student = new Student(name, age);
        student.setId(id);

        when(studentService.addStudent(name, age)).thenReturn(student);

        String request = objectMapper.writeValueAsString(student);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/student/post/{name}/{age}", name, age)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age));
    }

    @Test
    public void getStudentTest() throws Exception {

        String name = "Max";
        int age = 26;
        Long id = 1L;

        Student student = new Student(name, age);
        student.setId(id);

        String request = objectMapper.writeValueAsString(student);

        when(studentService.getStudent(id)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/{id}/get", id)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age));
    }

    @Test
    public void updateStudentTest() throws Exception {

        String updateName = "Pavel";
        int updateAge = 27;
        Long id = 1L;

        Student updateStudent = new Student(updateName, updateAge);
        updateStudent.setId(id);

        String request = objectMapper.writeValueAsString(updateStudent);

        when(studentService.updateStudent(id, updateName, updateAge)).thenReturn(updateStudent);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/student/{id}/update/{name}/{age}", id, updateName, updateAge)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updateName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(updateAge))
                .andReturn();
    }

    @Test
    public void removeStudentTest() throws Exception {

        String name = "Max";
        int age = 26;
        Long id = 1L;

        Student removedStudent = new Student(name, age);
        removedStudent.setId(id);

        String request = objectMapper.writeValueAsString(removedStudent);

        when(studentService.removeStudent(id)).thenReturn(removedStudent);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/student/{id}/remove", id)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age))
                .andReturn();
    }
}