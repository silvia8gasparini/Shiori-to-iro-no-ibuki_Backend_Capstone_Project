package it.epicode.finalproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DigitalCardDto {
    private int id;
    private String cardNumber;
    private LocalDate issuedAt;
    private int userId;
}
