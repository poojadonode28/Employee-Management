package com.example.EmployeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    //We use this for response
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
