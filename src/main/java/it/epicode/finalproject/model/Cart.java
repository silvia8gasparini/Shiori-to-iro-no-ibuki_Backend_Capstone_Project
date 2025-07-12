package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    private LocalDateTime lastUpdated;

    private boolean checkedOut = false;

}
