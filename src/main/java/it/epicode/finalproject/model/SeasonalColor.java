package it.epicode.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeasonalColor {
    @Id
    @GeneratedValue
    private int id;

    private String nameJapanese;
    private String nameItalian;
    private String rgb;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "micro_season_id")
    private MicroSeason microSeason;
}
