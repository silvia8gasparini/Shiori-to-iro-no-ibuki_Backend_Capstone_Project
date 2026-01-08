package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(length = 8000)
    private String description;
    private String imageUrl;

    @Enumerated
    private Availability availability;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seasonal_color_id")
    private SeasonalColor seasonalColor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "micro_season_id")
    private MicroSeason microSeason;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases = new ArrayList<>();
}
