package org.example.integrationTests.BankUser;

import org.example.TestContainersAbstractEnv;
import org.example.dto.CustomerDto;
import org.example.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BankCustomerIntTests extends TestContainersAbstractEnv {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenAnUser_shouldAddUserToDatabase () throws Exception {
        // Given
        //Date serialize problematique
        LocalDate dateOfBirth = LocalDate.of(1998,12,14);
        String firstName = "Patrick";
        String lastName = "Duchet";

        CustomerDto user = CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        String userJson = objectMapper.writeValueAsString(user);

        // when
        ResultActions response = mockMvc.perform(post("/api/v1/user")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(MockMvcResultMatchers.status().is(201));
        Assertions.assertEquals(1, customerRepository.findAll().size());
        Assertions.assertEquals(firstName, customerRepository.findAll().stream().findAny().get().getFirstName());
        Assertions.assertEquals(lastName, customerRepository.findAll().stream().findAny().get().getLastName());

    }
}
