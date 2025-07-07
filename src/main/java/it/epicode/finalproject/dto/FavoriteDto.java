package it.epicode.finalproject.dto;

import lombok.Data;

@Data
public class FavoriteDto {
    private int bookId;
    private String title;
    private String author;
    private String imageUrl;
}
