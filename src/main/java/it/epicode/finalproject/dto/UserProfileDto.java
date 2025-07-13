package it.epicode.finalproject.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String avatarUrl;
    private String digitalCard;
}
