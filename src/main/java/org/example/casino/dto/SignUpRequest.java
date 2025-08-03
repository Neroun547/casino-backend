package org.example.casino.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Data
public class SignUpRequest {
    @NotBlank
    @Length(min = 3, max = 30)
    String firstname;

    @NotBlank
    @Length(min = 3, max = 30)
    String lastname;

    @NotBlank
    @Length(min = 3, max = 256)
    String email;

    @NotBlank
    @Length(min = 6, max = 50)
    String password;

    @NotBlank
    @Length(min = 3, max = 20)
    String username;

    @NumberFormat
    int age;


}
