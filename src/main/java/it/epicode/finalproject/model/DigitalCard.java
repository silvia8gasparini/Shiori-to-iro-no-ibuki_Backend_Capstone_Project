package it.epicode.finalproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class DigitalCard {
    @Id
    @GeneratedValue
    private int id;

    private String cardNumber;
    private LocalDate issuedAt;

    @JsonIgnore
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "digitalCard")
    private List<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "digitalCard")
    private List<Borrow> borrows;
}
