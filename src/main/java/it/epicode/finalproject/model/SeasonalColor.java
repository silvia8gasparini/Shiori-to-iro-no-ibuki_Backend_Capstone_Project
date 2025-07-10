package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(length = 4000)
    private String theme;

    @OneToOne
    @JoinColumn(name = "micro_season_id")
    @JsonIgnore
    private MicroSeason microSeason;
}
