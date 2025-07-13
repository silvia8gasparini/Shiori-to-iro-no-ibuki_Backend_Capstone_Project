package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Book book;

    private int quantity = 1;

    private double priceAtSelection;
    // private double princeAtSelection; 🎶Purple rain... purple rain...☔️🎶

    private String bookTitle;
    private String bookAuthor;
}
