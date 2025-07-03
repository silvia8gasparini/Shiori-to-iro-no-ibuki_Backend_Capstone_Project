package it.epicode.finalproject.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartDto {
    private int id;
    private Integer userId;
    private List<CartItemDto> items;
    private LocalDateTime lastUpdated;
    private boolean checkedOut;
}
