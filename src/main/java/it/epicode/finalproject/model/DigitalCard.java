package it.epicode.finalproject.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class DigitalCard {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    private LocalDateTime lastUpdated;

    private boolean checkedOut = false;
}
