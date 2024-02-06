package org.example.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;

}
