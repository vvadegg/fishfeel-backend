package ru.fishfeel.dto;

import lombok.Data;

@Data
public class AuthenticatedRequestDto {
    private String email;
    private String password;
}
