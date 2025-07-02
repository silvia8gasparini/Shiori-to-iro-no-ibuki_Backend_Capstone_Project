package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Role;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private int id;
    private String name;
    private String surname;
    private String email;
    private String avatarUrl;
    private Role role;
}