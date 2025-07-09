package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Data
@Entity
public class MicroSeason {
    @Id
    @GeneratedValue
    private int id;
    private String japaneseName;
    private String italianName;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(mappedBy = "microSeason")
    private SeasonalColor seasonalColor;

    @JsonIgnore
    @OneToMany(mappedBy = "microSeason")
    private List<Book> books;

    public String getDisplayPeriod() {
        String startDay = String.valueOf(startDate.getDayOfMonth());
        String endDay = String.valueOf(endDate.getDayOfMonth());

        String startMonth = startDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
        String endMonth = endDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN);

        if (startDate.getMonth().equals(endDate.getMonth())) {
            return startDay + "–" + endDay + " " + startMonth;
        } else {
            return startDay + " " + startMonth + "–" + endDay + " " + endMonth;
        }
    }
}
