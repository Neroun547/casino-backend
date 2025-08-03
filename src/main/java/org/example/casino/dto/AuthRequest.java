package org.example.casino.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthRequest {
    @Length(min = 3, max = 256)
    @NotBlank
    String usernameOrEmail;

    @Length(min = 6, max = 30)
    @NotBlank
    String password;
}
