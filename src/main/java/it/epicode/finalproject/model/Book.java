package it.epicode.finalproject.model;

import it.epicode.finalproject.enumeration.Availability;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String author;
    private String year;
    private String publisher;
    private String isbn;
    private double price;
    private String imageUrl;
    private String description;

    @Enumerated
    private Availability availability;

    @ManyToOne
    private SeasonalColor seasonalColor;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases = new ArrayList<>();


}
