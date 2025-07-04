package it.epicode.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeasonalColor {
    @Id
    @GeneratedValue
    private int id;
    private String japaneseName;
    private String italianName;
    private String rgb;
    @Column(length = 1000)
    private String description;
    @Column(length = 4000)
    private String details;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "micro_season_id")
    private MicroSeason microSeason;
}
