package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Role;
import lombok.Data;

@Data
public class UserProfileDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String avatarUrl;
    private DigitalCardDto digitalCard;
    private Role role;
}
