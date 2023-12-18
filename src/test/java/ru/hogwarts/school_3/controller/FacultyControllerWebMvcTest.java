package ru.hogwarts.school_3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school_3.model.Faculty;
import ru.hogwarts.school_3.model.Student;
import ru.hogwarts.school_3.service.FacultyService;
import ru.hogwarts.school_3.service.StudentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addFacultyTest() throws Exception {

        String name = "TestFaculty";
        String color = "Red";
        Long id = 1L;

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(facultyService.addFaculty(name, color)).thenReturn(faculty);

        String request = objectMapper.writeValueAsString(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty/post/{name}/{color}", name, color)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyTest() throws Exception {

        String name = "TestFaculty";
        String color = "Red";
        Long id = 1L;

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        String request = objectMapper.writeValueAsString(faculty);

        when(facultyService.getFaculty(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/{id}/get", id)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color));
    }

    @Test
    public void updateFacultyTest() throws Exception {

        String updateName = "TestFaculty";
        String updateColor = "Red";
        Long id = 1L;

        Faculty updateFaculty = new Faculty(updateName, updateColor);
        updateFaculty.setId(id);

        String request = objectMapper.writeValueAsString(updateFaculty);

        when(facultyService.updateFaculty(id, updateName, updateColor)).thenReturn(updateFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/faculty/{id}/update/{name}/{color}", id, updateName, updateColor)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updateName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(updateColor))
                .andReturn();
    }

    @Test
    public void removeFacultyTest() throws Exception {

        String name = "TestFaculty";
        String color = "Red";
        Long id = 1L;

        Faculty removedFaculty = new Faculty(name, color);
        removedFaculty.setId(id);

        String request = objectMapper.writeValueAsString(removedFaculty);

        when(facultyService.removeFaculty(id)).thenReturn(removedFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/faculty/{id}/remove", id)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color))
                .andReturn();
    }
}