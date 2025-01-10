package com.app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    // Getters and setters
}
