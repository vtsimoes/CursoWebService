package com.victormartins.firstexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victormartins.firstexample.model.Project;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    MockMvc mvc;

    static String PROJECT_API = "/projects";

    @Test
    @DisplayName("Deve criar um project com sucesso.")
    public void createProjectTest() throws Exception{
        Project project = Project.builder()
                .id(14)
                .name("Teste de nome de projeto")
                .email("projeto@email.com")
                .build();

        String json = new ObjectMapper().writeValueAsString(project);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PROJECT_API).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE).content(json);

        mvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("name").isNotEmpty())
                .andExpect(jsonPath("name").value(project.getName()))
                .andExpect(jsonPath("email").isNotEmpty())
                .andExpect(jsonPath("email").value(project.getEmail()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Deve lançar um erro quando name e e-mail for inválido.")
    public void createInvalidProjectTest() throws Exception{
        Project project = Project.builder()
                .id(14)
                .name("aa")
                .email("projeto@")
                .build();

        String json = new ObjectMapper().writeValueAsString(project);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PROJECT_API)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorCount").value(2))
                .andExpect(jsonPath("code", ArgumentMatchers.contains("400")).value("400 BAD_REQUEST"))
                .andExpect(jsonPath("messages", Matchers.hasSize(2)))
                .andDo(MockMvcResultHandlers.print());
    }

}
