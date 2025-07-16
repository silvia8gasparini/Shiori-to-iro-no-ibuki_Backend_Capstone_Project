package it.epicode.finalproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowResponseDto {
    private int id;
    private LocalDate startDate;
    private LocalDate dueDate;
    private boolean returned;
    private String bookTitle;
    private String userName;
}
