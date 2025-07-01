package it.epicode.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class MicroSeason {
    @Id
    @GeneratedValue
    private int id;

    private String nameJapanese;
    private String nameItalian;


    @OneToOne(mappedBy = "microSeason")
    private SeasonalColor seasonalColor;

    @OneToMany(mappedBy = "microSeason")
    private List<Book> books;


}
